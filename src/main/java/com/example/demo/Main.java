package com.example.demo;


import com.example.demo.Application.Application;
import com.example.demo.model.Employee;
import com.example.demo.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class Main {

    @Autowired
    Application application;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    CommandLineRunner runner(Repository repository) {
        return (args) -> {
            Scanner scanner = new Scanner(System.in);

            //fixed
            application.createEmployee(new Employee("Mustafa", 150000.0, 0.0, 0, 0, 0, Employee.EmployeeType.SALARIED));
            application.createEmployee(new Employee("Aset", 100000.0, 0.0, 0, 0, 0, Employee.EmployeeType.SALARIED));
            //hourly
            application.createEmployee(new Employee("Baga", 0.0, 2000.0, 45, 0, 0, Employee.EmployeeType.HOURLY));
            application.createEmployee(new Employee("Zhas", 0.0, 3000.0, 35, 0, 0, Employee.EmployeeType.HOURLY));
            //Commision only
            application.createEmployee(new Employee("Aigul", 0.0, 0.0, 0, 0.25f, 250000, Employee.EmployeeType.COMMISION));
            application.createEmployee(new Employee("Zhanel", 0.0, 0.0, 0, 0.25f, 300000, Employee.EmployeeType.COMMISION));
            //fixed and commision
            application.createEmployee(new Employee("Alina", 60000.0, 0.0, 0, 0.15f, 200000, Employee.EmployeeType.SALARIED_COMMISION));
            application.createEmployee(new Employee("Kalina", 60000.0, 0.0, 0, 0.15f, 300000, Employee.EmployeeType.SALARIED_COMMISION));

            List<String> salaryList;

            while (true) {
                System.out.println(
                                "1. Calculate salary for Salaried Employees \n" +
                                "2. Calculate salary for Hourly Employees \n" +
                                "3. Calculate salary for Commission Employees \n" +
                                "4. Calculate salary for Salary and Commission Employees \n" +
                                "5. Increase salary for Salaried Employees \n" +
                                "6. Increase salary for Salary and Commission Employees"
                );

                int a = scanner.nextInt();
                switch (a) {
                    case 1:
                        salaryList = application.getTotalSalaryByType(Employee.EmployeeType.SALARIED);
                        result(salaryList);
                        break;
                    case 2:
                        salaryList = application.getTotalSalaryByType(Employee.EmployeeType.HOURLY);
                        result(salaryList);
                        break;
                    case 3:
                        salaryList = application.getTotalSalaryByType(Employee.EmployeeType.COMMISION);
                        result(salaryList);
                        break;
                    case 4:
                        salaryList = application.getTotalSalaryByType(Employee.EmployeeType.SALARIED_COMMISION);
                        result(salaryList);
                        break;
                    case 5:
                        System.out.println("Write percentage of increase");
                        int percent = scanner.nextInt();
                        application.increaseFixedSalaryByType(Employee.EmployeeType.SALARIED, percent);
                        break;
                    case 6:
                        System.out.println("Write percentage of increase");
                        percent = scanner.nextInt();
                        application.increaseFixedSalaryByType(Employee.EmployeeType.SALARIED_COMMISION, percent);
                        break;
                }
            }
        };

    }
    private void result(List<String> salaryList) {
        for(String s: salaryList)
            System.out.println(s);
    }

}
