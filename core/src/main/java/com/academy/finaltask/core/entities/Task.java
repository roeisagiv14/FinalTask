package com.academy.finaltask.core.entities;


import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.Date;

import static javax.persistence.CascadeType.PERSIST;

@SuperBuilder
@Entity
@Table(name ="tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "titleOfTask")
    private String titleOfTask;
    @ManyToOne(fetch = FetchType.LAZY)
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn(name = "employee_id")
    private Employee assigneeOfTask;
    @Column(name = "dueDateOfTask")
    private Date dueDateOfTask;
    //@Column(name = "statusOfTask")
    @Enumerated(EnumType.ORDINAL)
    private Status statusOfTask;

    public Task() {
    }

    public Task(Long id, String titleOfTask, Employee assigneeOfTask, Date dueDateOfTask, Status statusOfTask) {
        this.id = id;
        this.titleOfTask = titleOfTask;
        this.assigneeOfTask = assigneeOfTask;
        this.dueDateOfTask = dueDateOfTask;
        this.statusOfTask = statusOfTask;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitleOfTask() {
        return titleOfTask;
    }

    public void setTitleOfTask(String titleOfTask) {
        this.titleOfTask = titleOfTask;
    }

    public Employee getAssigneeOfTask() {
        return assigneeOfTask;
    }

    public void setAssigneeOfTask(Employee assigneeOfTask) {
        this.assigneeOfTask = assigneeOfTask;
    }

    public Date getDueDateOfTask() {
        return dueDateOfTask;
    }

    public void setDueDateOfTask(Date dueDateOfTask) {
        this.dueDateOfTask = dueDateOfTask;
    }

    public Status getStatusOfTask() {
        return statusOfTask;
    }

    public void setStatusOfTask(Status statusOfTask) {
        this.statusOfTask = statusOfTask;
    }
}
