package com.example.demo.event.handler;

import com.example.demo.event.SalaryIncreseEvent;
import com.example.demo.model.Employee;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SalaryIncreaseHandler implements ApplicationListener<SalaryIncreseEvent> {
    @Override
    public void onApplicationEvent(SalaryIncreseEvent salaryIncreseEvent) {
        Employee e = salaryIncreseEvent.getEmployee();
        System.out.println("Salary increased for: " + e.getName());
    }
}
