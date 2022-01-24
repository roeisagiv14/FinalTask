package com.academy.finaltask.core.services;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.*;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmployeeService employeeService;

    public ArrayList<Task> findall(){
     return (ArrayList<Task>) taskRepository.findAll();
    };

    public Task create(Task task) {return save(task);}

    public Task findTaskById(Long id){ return taskRepository.findTasksById(id);}

    public void deleteById(Long id){
        taskRepository.deleteById(id);
    }

    private Task save(Task task){

        Task savedTask = null;
        try{
            Employee newEmployee = task.getAssigneeOfTask();
            if(employeeService.existsByFirstAndLastName(newEmployee.getFirstNameOfEmployee(),newEmployee.getLastNameOfEmployee())){
                task.setAssigneeOfTask(employeeService.findByFirstAndLastName(newEmployee.getFirstNameOfEmployee(), newEmployee.getLastNameOfEmployee()));
            }
            else {
                employeeService.create(newEmployee);
            }
            savedTask = taskRepository.save(task);
        } catch (DataIntegrityViolationException e){
            throw  new EntityExistsException("Task already exists");
        }

        return  savedTask;
    }

}
