package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.api.generated.DefaultApi;
import com.academy.finaltask.api.generated.model.TaskRequest;
import com.academy.finaltask.api.generated.model.TaskResponse;
import com.academy.finaltask.api.generated.model.TasksResponse;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.services.TaskService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

@Controller
public class TaskController implements DefaultApi {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskConverter taskConverter;


    @Override
    public ResponseEntity<TaskResponse> addNewTask(TaskRequest taskRequest) {
        Task newTask = taskService.create(taskConverter.taskFromRequest(taskRequest));
        TaskResponse taskResponse = taskConverter.toTaskResponse(newTask);
        return ResponseEntity.status(HttpStatus.CREATED).body(taskResponse);
    }

    @Override
    public ResponseEntity<TasksResponse> getAllTasks(){
        TasksResponse tasksResponse = taskConverter.toTasksResponse(taskService.findall());
        return ResponseEntity.status(HttpStatus.FOUND).body(tasksResponse);
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id){
        taskService.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<TaskResponse> updateTask(Long id, TaskRequest taskRequest){
        taskService.throwIfTaskNotExists(id);
        Task task = taskService.create((taskConverter.taskFromRequestById(taskRequest, id)));
        TaskResponse taskResponseFromTask = taskConverter.toTaskResponse(task);
        return ResponseEntity.ok().body(taskResponseFromTask);
    }


/*

    @PutMapping(path = "/edit/{id}")
    public ResponseEntity<String> editTask(@PathVariable Long id, RequestEntity<String> stringRequestEntity) throws ParseException, org.json.JSONException {
        taskService.create((taskConverter.taskFromRequestById(stringRequestEntity, id)));
        return ResponseEntity.ok().body("Task with id:" + id + " updated");
    }


*/

}
