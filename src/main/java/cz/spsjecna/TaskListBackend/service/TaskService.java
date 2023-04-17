package cz.spsjecna.TaskListBackend.service;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;

import java.util.List;

public interface TaskService {
    List<TaskModel> ReadTasks(Long userId);
    boolean SaveTasks(TaskDTO[] taskDTOS, Long userId);
}
