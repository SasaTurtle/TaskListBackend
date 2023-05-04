package cz.spsjecna.TaskListBackend.controller;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;
import cz.spsjecna.TaskListBackend.model.User;
import cz.spsjecna.TaskListBackend.repository.UserRepository;
import cz.spsjecna.TaskListBackend.security.jwt.JwtUtils;
import cz.spsjecna.TaskListBackend.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/task")
@Slf4j
public class TaskController {
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JwtUtils jwtUtils;
  @Autowired
  TaskService taskService;

  @GetMapping("/all")
  public String allAccess() {
    return "Public Content.";
  }

  /**
   *
   * @param token
   * @return
   */
  @GetMapping("/")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public ResponseEntity<List<TaskModel>> getTasks(@RequestHeader (name="Authorization") String token) {
    List<TaskModel> result = new ArrayList<>();
    try {
      String userName = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
      Optional<User> u = userRepository.findByUsername(userName);

      if (u != null) {
        result = taskService.ReadTasks(u.get().getId());
        log.info("TASK REQUEST: " + u.get().getUsername());
      }
      return ResponseEntity.ok(result);
    }
       catch (Exception ex){
    log.error(ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);
  }
  }

  /**
   *
   * @param tasks
   * @param token
   * @return
   */
  @PostMapping("/")
  @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
  public Boolean createTask(@RequestBody TaskDTO[] tasks, @RequestHeader (name="Authorization") String token) {
    String userName = jwtUtils.getUserNameFromJwtToken(token.replace("Bearer ", ""));
    Optional<User> u = userRepository.findByUsername(userName);
    boolean result = false;
    if (u != null && tasks.length>0) {
      result = taskService.SaveTasks(tasks,u.get().getId());
      log.info("CREATED TASK: " + u.get().getUsername() + " | TASK NAME: " + tasks[0].getName());
    }


    return result;
  }

  @GetMapping("/mod")
  @PreAuthorize("hasRole('MODERATOR')")
  public String moderatorAccess() {
    return "Moderator Board.";
  }

  @GetMapping("/admin")
  @PreAuthorize("hasRole('ADMIN')")
  public String adminAccess() {
    return "Admin Board.";
  }
}
