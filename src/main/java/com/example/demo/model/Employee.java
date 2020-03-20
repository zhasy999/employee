package com.example.demo.model;

import javax.persistence.*;

@Entity
@Table(name = "lab6")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "fixed")
    private double fixedSalary;

    @Column(name = "hour_rate")
    private double hourRate;

    @Column(name = "hours_work")
    private int hoursWorked;

    @Column(name = "comm")
    private float commRate;

    @Column
    private int sales;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private EmployeeType emplType;

    public enum EmployeeType {
        SALARIED,
        HOURLY,
        COMMISION,
        SALARIED_COMMISION
    }

    public Employee(){

    }

    public Employee(String name, double fixedSalary, double hourRate, int hoursWorked, float commRate, int sales, EmployeeType emplType) {
        this.name = name;
        this.fixedSalary = fixedSalary;
        this.hourRate = hourRate;
        this.hoursWorked = hoursWorked;
        this.commRate = commRate;
        this.sales = sales;
        this.emplType = emplType;
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public double getFixedSalary() {
        return fixedSalary;
    }
    public void setFixedSalary(double fixedSalary) {
        this.fixedSalary = fixedSalary;
    }

    public double getHourRate() {
        return hourRate;
    }
    public void setHourRate(double hourRate) {
        this.hourRate = hourRate;
    }

    public int getHoursWorked() {
        return hoursWorked;
    }
    public void setHoursWorked(int hoursWorked) {
        this.hoursWorked = hoursWorked;
    }

    public float getCommRate() {
        return commRate;
    }
    public void setCommRate(float commRate) {
        this.commRate = commRate;
    }


    public EmployeeType getEmplType() {
        return emplType;
    }
    public void setEmplType(EmployeeType emplType) {
        this.emplType = emplType;
    }


    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }


}
