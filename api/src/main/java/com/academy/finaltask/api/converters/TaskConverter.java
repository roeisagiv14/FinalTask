package com.academy.finaltask.api.converters;

import com.academy.finaltask.api.generated.model.*;
import com.academy.finaltask.core.entities.Status;
import com.academy.finaltask.core.entities.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
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

//    public Task taskFromRequest(RequestEntity<String> stringRequestEntity) throws JSONException, ParseException {
//        return taskBuilderFromJSONObj(new JSONObject(stringRequestEntity.getBody()));
//    }

    public Task taskFromRequest(TaskRequest taskRequest){
        TaskRequestTask task = taskRequest.getTask();
        return Task.builder()
                .titleOfTask(task.getTitle())
                .assigneeOfTask(employeeConverter.employeeFromRequest(task.getAssignee()))
                .statusOfTask(getStatusFromString(task.getStatus()))
                .dueDateOfTask(task.getDuedate())
                .build();
    }

    public TaskResponse toTaskResponse(Task task){
        TaskResponseTask taskResponseTask = new TaskResponseTask();
        taskResponseTask.setId(task.getId());
        taskResponseTask.setTitle(task.getTitleOfTask());
        taskResponseTask.setAssignee(employeeConverter.toEmployeeResponse(task.getAssigneeOfTask()));
        taskResponseTask.setDuedate(task.getDueDateOfTask());
        taskResponseTask.setStatus(task.getStatusOfTask().toString());
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setTask(taskResponseTask);

        return taskResponse;
    }

    public TasksResponse toTasksResponse(ArrayList<Task> tasks){
        TasksResponse allTasks = new TasksResponse();
        for(Task task : tasks){
            allTasks.addTasksItem(toTaskResponse(task));
        }

        //allTasks.setTasks(tasks);
        return allTasks;
    }

    public Task taskFromRequestById(TaskRequest taskRequest, Long id){
        Task updatedTask = taskFromRequest(taskRequest);
        return new Task(id,
                updatedTask.getTitleOfTask(),
                updatedTask.getAssigneeOfTask(),
                updatedTask.getDueDateOfTask(),
                updatedTask.getStatusOfTask());
    }




//    public Task taskFromRequestById(RequestEntity<String> stringRequestEntity, Long id) throws JSONException, ParseException {
//        return  taskBuilderFromJSONObjById(new JSONObject(stringRequestEntity.getBody()), id);
//    }

//    public JSONObject toTaskResponse(Task task) throws JSONException {
//        return new JSONObject()
//                .put("Id:", task.getId())
//                .put("Title:", task.getTitleOfTask())
//                .put("Assignee:", employeeConverter.employeeToJSONObj(task))
//                .put("Due date:", task.getDueDateOfTask().toString())
//                .put("Status", task.getStatusOfTask().toString());
//    }

//    public JSONArray toTasksResponse(ArrayList<Task> tasks) throws JSONException {
//        JSONArray allTasks = new JSONArray();
//        for(Task task : tasks){
//            allTasks.put(toTaskResponse(task));
//        }
//         return allTasks;
//    }

//    private Task taskBuilderFromJSONObjById(JSONObject JSONObjFromStringRequestEntity, Long id) throws JSONException, ParseException {
//        return new Task(id,
//                JSONObjFromStringRequestEntity.getString("titleOfTask"),
//                employeeConverter.employeeFromJSONObj((JSONObject) JSONObjFromStringRequestEntity.get("assigneeOfTask")),
//                new SimpleDateFormat("ddMMyyyy").parse(JSONObjFromStringRequestEntity.getString("dueDateOfTask")),
//                getStatusFromString(JSONObjFromStringRequestEntity.getString("statusOfTask")));
//    }
//
//
//    private Task taskBuilderFromJSONObj(JSONObject JSONObjFromStringRequestEntity) throws JSONException, ParseException {
//        return Task.builder()
//                .titleOfTask(JSONObjFromStringRequestEntity.getString("titleOfTask"))
//                .assigneeOfTask(employeeConverter.employeeFromJSONObj((JSONObject) JSONObjFromStringRequestEntity.get("assigneeOfTask")))
//                .dueDateOfTask(new SimpleDateFormat("ddMMyyyy").parse(JSONObjFromStringRequestEntity.getString("dueDateOfTask")))
//                .statusOfTask(getStatusFromString(JSONObjFromStringRequestEntity.getString("statusOfTask")))
//                .build();
//
//    }

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
