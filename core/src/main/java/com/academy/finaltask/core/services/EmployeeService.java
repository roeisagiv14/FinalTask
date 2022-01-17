package com.academy.finaltask.core.services;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee create(Employee employee){return  employeeRepository.save(employee); }

    public boolean existsByFirstAndLastName(String firstNameOfEmployee, String lastNameOfEmployee){
        return employeeRepository.existsByFirstNameOfEmployeeAndLastNameOfEmployee(firstNameOfEmployee, lastNameOfEmployee);
    }

    public Employee findByFirstAndLastName(String firstNameOfEmployee, String lastNameOfEmployee){
        return employeeRepository.findByFirstNameOfEmployeeAndLastNameOfEmployee(firstNameOfEmployee, lastNameOfEmployee);
    }
}
