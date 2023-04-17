package cz.spsjecna.TaskListBackend.repository;

import cz.spsjecna.TaskListBackend.model.TaskModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskModel, Long> {

    @Query(value = "select t.* from task t where t.userid = :userid", nativeQuery = true)
    public List<TaskModel> findTaskByUser(@Param("userid") Long userid);
}
