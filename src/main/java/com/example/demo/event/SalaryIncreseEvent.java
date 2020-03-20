package com.example.demo.event;

import com.example.demo.model.Employee;
import org.springframework.context.ApplicationEvent;

public class SalaryIncreseEvent extends ApplicationEvent {

    private Employee employee;

    public SalaryIncreseEvent(Object source, Employee employee) {
        super(source);
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }
}
