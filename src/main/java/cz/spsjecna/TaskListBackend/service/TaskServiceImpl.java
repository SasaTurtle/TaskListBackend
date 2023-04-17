package cz.spsjecna.TaskListBackend.service;

import cz.spsjecna.TaskListBackend.model.TaskDTO;
import cz.spsjecna.TaskListBackend.model.TaskModel;
import cz.spsjecna.TaskListBackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{
    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskModel> ReadTasks(Long userId) {
        return taskRepository.findTaskByUser(userId);
    }

    @Override
    public boolean SaveTasks(TaskDTO[] taskDTOS,Long userId) {
        List<TaskModel> taskModelList = new ArrayList<>();
        for (TaskDTO task:taskDTOS) {
            taskModelList.add(new TaskModel((long)task.getId(),task.getName(),task.getDescription(),task.getDateFrom(),task.getDateTo(),task.getPriority(),task.getStatus(),userId));
        }
        taskRepository.saveAll(taskModelList);
        return true;
    }
}
