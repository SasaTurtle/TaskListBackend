package cz.spsjecna.TaskListBackend.service;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;

import java.util.List;
import java.util.UUID;

/**
 * Business logic for task manipulation
 */

public interface TaskService {
    List<TaskModel> ReadTasks(Long userId);
    boolean SaveTasks(TaskDTO[] taskDTOS, Long userId);
    boolean DeleteTask(UUID taskID, Long userId);
}
