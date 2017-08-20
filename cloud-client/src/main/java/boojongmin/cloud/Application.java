package boojongmin.cloud;


import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@SpringBootApplication
@EnableDiscoveryClient
//@EnableEurekaClient
@EnableFeignClients
public class Application 
{
    public static void main( String[] args )
    {
    	 new SpringApplicationBuilder(Application.class)
         .web(true)
         .run(args);
    }
    
}
//
//@Component
//class DiscoveryClientExample implements CommandLineRunner {
//
//    @Autowired
//    private DiscoveryClient discoveryClient;
//
//    @Override
//    public void run(String... strings) throws Exception {
//        discoveryClient.getInstances("api-service").forEach((ServiceInstance s) -> {
//            System.out.println(ToStringBuilder.reflectionToString(s));
//        });
//    }
//}

@Component
class FeignExample implements CommandLineRunner {

    @Autowired
    private StudentClient studentClient;

    @Override
    public void run(String... strings) throws Exception {
        Student student = this.studentClient.getStudentByName("boojongmin");
        System.out.println(student);
        
    }
}

@FeignClient("api-service")
interface StudentClient {

    @RequestMapping(method = RequestMethod.GET, value = "/student/{name}")
    Student getStudentByName(@PathVariable("name") String name);
}


@Getter
@Setter
@ToString
class Student {
	private String name;
	private int number;
}



@RestController
@RequestMapping("/student")
class StudentController {
	
    @Autowired
    private StudentClient studentClient;
	
	@RequestMapping("/{name}")
	public String getStudentByName(@PathVariable("name") String name) {
		Student s = studentClient.getStudentByName(name);
		return String.format("student name: %s, number: %d", s.getName(), s.getNumber());
	}
}
