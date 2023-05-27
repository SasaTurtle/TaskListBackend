package cz.spsjecna.TaskListBackend.controller;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskDeleteDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;
import cz.spsjecna.TaskListBackend.model.User;
import cz.spsjecna.TaskListBackend.repository.UserRepository;
import cz.spsjecna.TaskListBackend.security.jwt.JwtUtils;
import cz.spsjecna.TaskListBackend.service.TaskService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Unit tests for task crud (create,read,delete,update)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskControlerTest {
    @Autowired
    private TaskController taskController;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private TaskService taskService;
    @Autowired
    AuthenticationManager authenticationManager;

    UUID taskID;

    public TaskControlerTest() {
        taskID = UUID.randomUUID();
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = {"USER"})
    public void testCreateTaskWithValidDetailsAndToken() {

        TaskDTO[] tasks = new TaskDTO[1];
        tasks[0] = new TaskDTO();
        tasks[0].setName("Test Task");
        tasks[0].setDescription("Test Description");
        tasks[0].setPriority(TaskDTO.Priority.LOW);
        tasks[0].setStatus(TaskDTO.Status.NOT_STARTED);
        tasks[0].setDateTo(new Date(2000, 11, 21));
        tasks[0].setDateFrom(new Date(2001, 11, 21));
        tasks[0].setId(taskID);


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("testuser", "testpassword"));
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        Boolean response = taskController.createTask(tasks, token);
        assertThat(response).isEqualTo(true);
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = {"USER"})
    public void testGetTasksWithValidToken() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("testuser", "testpassword"));
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        ResponseEntity<List<TaskModel>> response = taskController.getTasks(token);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isInstanceOf(List.class);
    }

    @Test
    @WithMockUser(username = "testuser", password = "testpassword", roles = {"USER"})
    public void testDeleteTaskWithValidIdAndToken() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("testuser", "testpassword"));
        String token = "Bearer " + jwtUtils.generateJwtToken(authentication);
        Boolean response = taskController.deleteTask(taskID.toString(),token);
        assertThat(response).isEqualTo(true);
    }







}
