package com.academy.finaltask.api.converters;

import com.academy.finaltask.api.generated.model.EmployeeRequest;
import com.academy.finaltask.api.generated.model.EmployeeRequestEmployee;
import com.academy.finaltask.api.generated.model.EmployeeResponse;
import com.academy.finaltask.api.generated.model.EmployeeResponseEmployee;
import com.academy.finaltask.core.entities.Employee;
import com.academy.finaltask.core.entities.Task;
import org.json.JSONObject;
import org.json.JSONException;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Component;

import java.text.ParseException;

@Component
public class EmployeeConverter {


//    public Employee employeeFromRequest(RequestEntity<String> stringRequestEntity) throws JSONException {
//        return employeeFromJSONObj(new JSONObject(stringRequestEntity.getBody()));
//    }

    public Employee employeeFromRequest(EmployeeRequest employeeRequest){
        EmployeeRequestEmployee employee = employeeRequest.getEmployee();
        return Employee.builder()
                .firstNameOfEmployee(employee.getFirstName())
                .lastNameOfEmployee(employee.getLastName())
                .build();
    }

    public  Employee employeeFromJSONObj(JSONObject JSONObjFromStringRequestEntity) throws JSONException{
        return Employee.builder()
                .firstNameOfEmployee(JSONObjFromStringRequestEntity.getString("firstNameOfEmployee"))
                .lastNameOfEmployee(JSONObjFromStringRequestEntity.getString("lastNameOfEmployee"))
                .build();
    }

    public EmployeeResponse toEmployeeResponse(Employee employee) {
        EmployeeResponseEmployee employeeResponseEmployee = new EmployeeResponseEmployee();
        employeeResponseEmployee.setId(employee.getId());
        employeeResponseEmployee.setFirstName(employee.getFirstNameOfEmployee());
        employeeResponseEmployee.setLastName(employee.getLastNameOfEmployee());
        EmployeeResponse employeeResponse = new EmployeeResponse();
        employeeResponse.setEmployee(employeeResponseEmployee);

        return employeeResponse;
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
