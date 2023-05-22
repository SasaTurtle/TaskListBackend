package cz.spsjecna.TaskListBackend.service;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;
import cz.spsjecna.TaskListBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    /**
     * @param userId
     * @return
     */
    @Override
    public List<TaskModel> ReadTasks(Long userId) {
        return taskRepository.findTaskByUser(userId);
    }

    /**
     * @param taskDTOS
     * @param userId
     * @return
     */
    @Override
    public boolean SaveTasks(TaskDTO[] taskDTOS,Long userId) {
        List<TaskModel> taskModelList = new ArrayList<>();
        for (TaskDTO task:taskDTOS) {
            taskModelList.add(new TaskModel((UUID) task.getId(),task.getName(),task.getDescription(),task.getDateFrom(),task.getDateTo(),task.getPriority(),task.getStatus(),userId));
        }
        taskRepository.saveAll(taskModelList);
        return true;
    }

    @Override
    public boolean DeleteTask(UUID taskID, Long userId) {
        taskRepository.deleteById(taskID);
        return false;
    }
}
