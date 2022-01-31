package com.academy.finaltask.core.repositories;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {

    Task findTasksById(Long id);

}
