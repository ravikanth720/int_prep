package com.github.ravikanth720.json.pojo;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.xml.bind.PropertyException;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class Employee {
    Map<String, Object> properties = new LinkedHashMap<>();
    Compensation compensation;
    String firstName;

    @JsonGetter
    public String getFirstName() {
        return firstName;
    }

    @JsonSetter
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    @JsonGetter
    public Compensation getCompensation() {
        return compensation;
    }

    @JsonSetter
    public void setCompensation(Compensation compensation) {
        this.compensation = compensation;
    }

    @JsonAnySetter
    public void setProperty(String key, Object value) {
        properties.put(key, value);
    }

    @JsonAnyGetter
    public Object getProperty(String key) {
        return properties.get(key);
        
    }

    public double getBaseCompensation() {
        return this.compensation != null ? this.compensation.getBase() : 0;
    }

    static class Compensation {
        double base;
        double bonus;

        public double getBase() {
            return base;
        }

        public double getBonus() {
            return bonus;
        }
    }
}
