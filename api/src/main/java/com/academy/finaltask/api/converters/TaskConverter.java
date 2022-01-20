package com.academy.finaltask.api.converters;

import com.academy.finaltask.core.entities.Status;
import com.academy.finaltask.core.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Component
public class TaskConverter {

    @Autowired
    EmployeeConverter employeeConverter;

    public Task taskFromRequest(RequestEntity<String> stringRequestEntity) throws JSONException, ParseException {
        return taskBuilderFromJSONObj(new JSONObject(stringRequestEntity.getBody()));
    }

    public JSONObject toTaskResponse(Task task) throws JSONException {
        return new JSONObject()
                .put("Id:", task.getId())
                .put("Title:", task.getTitleOfTask())
                .put("Assignee:", employeeConverter.employeeToJSONObj(task))
                .put("Due date:", task.getDueDateOfTask().toString())
                .put("Status", task.getStatusOfTask().toString());
    }

    public JSONArray toTasksResponse(ArrayList<Task> tasks) throws JSONException {
        JSONArray allTasks = new JSONArray();
        for(Task task : tasks){
            allTasks.put(toTaskResponse(task));
        }
         return allTasks;
    }

    private Task taskBuilderFromJSONObj(JSONObject JSONObjFromStringRequestEntity) throws JSONException, ParseException {
        return Task.builder()
                .titleOfTask(JSONObjFromStringRequestEntity.getString("titleOfTask"))
                .assigneeOfTask(employeeConverter.employeeFromJSONObj((JSONObject) JSONObjFromStringRequestEntity.get("assigneeOfTask")))
                .dueDateOfTask(new SimpleDateFormat("ddMMyyyy").parse(JSONObjFromStringRequestEntity.getString("dueDateOfTask")))
                .statusOfTask(getStatusFromString(JSONObjFromStringRequestEntity.getString("statusOfTask")))
                .build();

    }

    private Status getStatusFromString(String stringStatus){
        Status statusFromString = null;
        switch (stringStatus){
            case "Done":
                statusFromString = Status.Done;
                break;
            case "InProccess":
                statusFromString = Status.InProccess;
                break;
            case "NeedToStart":
                statusFromString = Status.NeedToStart;
                break;
        }

        return  statusFromString;
    }
}
