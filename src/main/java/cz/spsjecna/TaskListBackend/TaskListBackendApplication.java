package cz.spsjecna.TaskListBackend;

import cz.spsjecna.TaskListBackend.model.ERole;
import cz.spsjecna.TaskListBackend.model.Role;
import cz.spsjecna.TaskListBackend.repository.RoleRepository;
import cz.spsjecna.TaskListBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TaskListBackendApplication {

	@Autowired

	public static void main(String[] args) {
		SpringApplication.run(TaskListBackendApplication.class, args);

	}

}
