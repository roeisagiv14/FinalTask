package com.academy.finaltask.core.services;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
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

    public Task update(Task task) throws EntityNotFoundException {
        Long id = task.getId();

        Optional<Task> optionalExistingTask = Optional.ofNullable(findTaskById(id));
        if (optionalExistingTask.isEmpty()) {
            String errorMsg = "Unable to update task, task not found";
            throw new EntityNotFoundException(errorMsg);
        }

        Task existingTask = optionalExistingTask.get();
        existingTask.setTitleOfTask(task.getTitleOfTask());
        existingTask.setAssigneeOfTask(task.getAssigneeOfTask());
        existingTask.setDueDateOfTask(task.getDueDateOfTask());
        existingTask.setStatusOfTask(task.getStatusOfTask());

        Task updatedTask = taskRepository.save(existingTask);
        return updatedTask;
    }

}
