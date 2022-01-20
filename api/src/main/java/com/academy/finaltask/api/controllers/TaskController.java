package com.academy.finaltask.api.controllers;


import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.core.entities.Task;
import com.academy.finaltask.core.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Set;

@RestController
@RequestMapping(path ="/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;
    @Autowired
    private TaskConverter taskConverter;


    @GetMapping(path = "/all")
    public ResponseEntity<String> getAllTasks() throws JSONException {
        ArrayList<Task> tasks = taskService.findall();
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskConverter.toTasksResponse(tasks).toString());
    }

    @PostMapping(path = "/add")
    public ResponseEntity<String> addNewTask(RequestEntity<String> stringRequestEntity) throws JSONException, ParseException {
        Task newTask = taskService.create(taskConverter.taskFromRequest(stringRequestEntity));
        return ResponseEntity.status(HttpStatus.FOUND).body(taskConverter.toTaskResponse(newTask).toString());
    }

    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<String> deleteTask(@PathVariable Long id){
        taskService.deleteById(id);
       return ResponseEntity.ok().body("Task with id:" + id + " deleted");
    }



}
