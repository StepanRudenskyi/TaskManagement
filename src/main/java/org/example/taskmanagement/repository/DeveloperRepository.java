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
    @Query(value = "SELECT d.id, d.name, COUNT(t.id) AS activeTasks" +
            " FROM Developer AS d" +
            " JOIN Task AS t ON d.id = t.developer.id " +
            " WHERE t.status = 'IN_PROGRESS'" +
            " GROUP BY d.id, d.name")
    List<DeveloperNameProjection> findAllDevelopersWithActiveTasksCount();

    Developer findByEmail(String email);
}

