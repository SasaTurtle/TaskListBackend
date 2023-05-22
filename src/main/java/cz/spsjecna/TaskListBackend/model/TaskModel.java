package cz.spsjecna.TaskListBackend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Data
@Entity
@Table(name = "task")
@AllArgsConstructor
@NoArgsConstructor
public class TaskModel {

    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, unique = true, columnDefinition = "uuid")
    private UUID id;

    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @Column(name="date_from")
    private Date dateFrom;
    @Column(name="date_to")
    private Date dateTo;
    @Column(name="status")
    private int status;
    @Column(name="priority")
    private int priority;
    @Column(name="userid")
    private Long userid;

    public TaskModel(UUID id, String name, String description, Date dateFrom, Date dateTo, TaskDTO.Priority priority, TaskDTO.Status status, Long userID) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.priority = priority.getValue();
        this.status = status.getValue();
        this.userid = userID;
    }
}
