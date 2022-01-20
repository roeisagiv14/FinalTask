package com.academy.finaltask.core.repositories;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    boolean existsByFirstNameOfEmployeeAndLastNameOfEmployee(String firstNameOfEmployee, String lastNameOfEmployee);

    Employee findByFirstNameOfEmployeeAndLastNameOfEmployee(String firstNameOfEmployee, String lastNameOfEmployee);

    //Set<Task> findAllBy(Employee employeeName);
}
