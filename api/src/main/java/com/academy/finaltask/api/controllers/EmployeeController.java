package com.academy.finaltask.api.controllers;

import com.academy.finaltask.api.converters.EmployeeConverter;
import com.academy.finaltask.api.converters.TaskConverter;
import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import java.text.ParseException;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    @Autowired
    private TaskConverter taskConverter;
    @Autowired
    private EmployeeConverter employeeConverter;
    @Autowired
    private EmployeeService employeeService;

    @PostMapping(path = "/add")
    public ResponseEntity<String> createNewEmployee(RequestEntity<String> stringRequestEntity) throws ParseException, EntityExistsException {
        try{
            Employee createdEmployee = employeeService.create(employeeConverter.employeeFromRequest(stringRequestEntity));
            return ResponseEntity.status(HttpStatus.CREATED).body(employeeConverter.toEmployeeResponse(createdEmployee));
        } catch (EntityExistsException e){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This employee is already exists!");
        } catch (JSONException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error with JSON format!");
        }
    }


}
