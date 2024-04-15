package org.example.taskmanagement.repository;

import org.example.taskmanagement.model.Developer;
import org.example.taskmanagement.projection.DeveloperNameProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {

    // get all developers with active tasks
    @Query(value = " SELECT DISTINCT d.id, d.name" +
            " FROM Developer d" +
            " JOIN Task AS t ON d.id = t.developer.id" +
            " WHERE t.status = 'IN_PROGRESS'")
    List<DeveloperNameProjection> findAllActiveTasks();

    // get all developers with the number of his active tasks
    @Query(value = "select d " +
            " FROM  Developer d LEFT JOIN FETCH d.tasks AS t" +
            " WHERE t.status = 'IN_PROGRESS'" +
            " GROUP BY t.developer.id" + 
            " HAVING count(t.id) > 0"
             )
    List<Developer> findAllDevelopersWithActiveTasksCount();

    Developer findByEmail(String email);
}

