package cz.spsjecna.TaskListBackend.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class TaskDeleteDTO {
        private UUID id;
    }

