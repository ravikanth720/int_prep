package com.github.ravikanth720.json;

import java.io.File;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.ravikanth720.json.pojo.Employee;

public class JsonHandling {
    List<Employee> employees;

    JsonHandling(String path) throws Exception {
        ObjectMapper om = new ObjectMapper();
        employees = om.readValue(new File(path), new TypeReference<List<Employee>>(){});
    }

    double getTotalCompensationBy(String key, String value) {
        return employees.stream()
        .filter(e -> e.getProperty(key).equals(value))
        .mapToDouble(Employee::getBaseCompensation)
        .sum();
    }

    public static void main(String[] args) throws Exception {
        JsonHandling jh = new JsonHandling("java-interview-prep\\src\\main\\resources\\employee_data.json");
        double totalComp = jh.getTotalCompensationBy("department", "Legal");

        System.out.println(String.format("Total compensation by department = %s", totalComp));
    }
}
