package boojongmin.cloud;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SpringBootApplication
//@EnableDiscoveryClient
@EnableEurekaClient
public class Application 
{
    public static void main( String[] args )
    {
    	SpringApplication.run(Application.class, args);
    }
}

@Configuration
class MockData {
	@Bean
	List<Student> students() {
		return Arrays.asList(new Student("boojongmin", 1), new Student("boojongmin_2", 2), new Student("student_3", 3));
	}
}


@Getter
@Setter
@ToString
@AllArgsConstructor
class Student {
	private String name;
	private int number;
}

@RestController
@RequestMapping("/student")
class StudentController {
	
	@Autowired List<Student> students;
	
	@RequestMapping("/{name}")
	public Student getStudentByName(@PathVariable("name") String name) {
		return students.stream().filter(x -> x.getName().equals(name)).findFirst().get();
	}
	
	
}
