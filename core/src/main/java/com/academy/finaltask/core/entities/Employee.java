package com.academy.finaltask.core.entities;

import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;

@SuperBuilder
@Entity
@Table(name = "employee")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="id")
    private Long id;

    @Column(name = "firstNameOfEmployee")
    private String firstNameOfEmployee;
    @Column(name = "lastNameOfEmployee")
    private String lastNameOfEmployee;
    @OneToMany(mappedBy = "assigneeOfTask")
    private Set<Task> setOfTasksOfEmployee;

    public Employee() {
    }

    public Employee(String firstNameOfEmployee, String lastNameOfEmployee, Set<Task> setOfTasksOfEmployee) {
        this.firstNameOfEmployee = firstNameOfEmployee;
        this.lastNameOfEmployee = lastNameOfEmployee;
        this.setOfTasksOfEmployee = setOfTasksOfEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstNameOfEmployee() {
        return firstNameOfEmployee;
    }

    public void setFirstNameOfEmployee(String firstNameOfEmployee) {
        this.firstNameOfEmployee = firstNameOfEmployee;
    }

    public String getLastNameOfEmployee() {
        return lastNameOfEmployee;
    }

    public void setLastNameOfEmployee(String lastNameOfEmployee) {
        this.lastNameOfEmployee = lastNameOfEmployee;
    }

    public Set<Task> getSetOfTasksOfEmployee() {
        return setOfTasksOfEmployee;
    }

    public void setSetOfTasksOfEmployee(Set<Task> setOfTasksOfEmployee) {
       this.setOfTasksOfEmployee = setOfTasksOfEmployee;
    }
}