package cz.spsjecna.TaskListBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main spring class
 */
@SpringBootApplication
public class TaskListBackendApplication {

	@Autowired
	public static void main(String[] args) {
		SpringApplication.run(TaskListBackendApplication.class, args);

	}

}
