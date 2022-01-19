package com.academy.finaltask.api.converters;

import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EmployeeConverter {

    public Employee employeeFromRequest(RequestEntity<String> stringRequestEntity) throws JSONException {
        return employeeFromJSONObj(new JSONObject(stringRequestEntity.getBody()));
    }

    public  Employee employeeFromJSONObj(JSONObject JSONObjFromStringRequestEntity) throws JSONException{
        return Employee.builder()
                .firstNameOfEmployee(JSONObjFromStringRequestEntity.getString("firstNameOfEmployee"))
                .lastNameOfEmployee(JSONObjFromStringRequestEntity.getString("lastNameOfEmployee"))
                .build();
    }

    public String toEmployeeResponse(Employee employee) throws JSONException {
        try{
            return new JSONObject()
                    .put("Id:", employee.getId())
                    .put("First Name:", employee.getFirstNameOfEmployee())
                    .put("Last Name:", employee.getLastNameOfEmployee())
                    .toString();
        }catch (JSONException e) {
                return "Error parsing employee JSON";
        }
    }

    public JSONObject employeeToJSONObj(Task task){
        try{
            JSONObject employeeObj = new JSONObject();
            employeeObj.put("First Name: ", task.getAssigneeOfTask().getFirstNameOfEmployee());
            employeeObj.put("Last Name: ", task.getAssigneeOfTask().getLastNameOfEmployee());
            return  employeeObj;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
