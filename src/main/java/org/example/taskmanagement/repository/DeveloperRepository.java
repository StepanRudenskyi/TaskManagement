package org.example.taskmanagement.repository;

import org.example.taskmanagement.model.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    // get all developers with active tasks
    @Query(value = " SELECT DISTINCT Developer.id" +
            " FROM Developer" +
            " JOIN Task ON Developer.id = Task.developer_id" +
            " AND Task.status = 'IN_PROGRESS'")
    List<Developer> findAllActiveTasks();

    // get all developers with the number of his active tasks
    @Query(value = "SELECT d.id, d.name, COUNT(t.id) AS ActiveTasks" +
            " FROM Developer AS d" +
            " JOIN Task AS t ON d.id = t.developer_id " +
            " AND t.status = 'IN_PROGRESS'" +
            " GROUP BY d.id, d.name")
    List<Developer> findAllDevelopersWithActiveTasksCount();

}

