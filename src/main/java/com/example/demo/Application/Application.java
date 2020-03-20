package com.example.demo.Application;
import com.example.demo.event.SalaryIncreseEvent;
import com.example.demo.model.Employee;
import com.example.demo.repo.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import static com.example.demo.model.Employee.EmployeeType.SALARIED;
import static com.example.demo.model.Employee.EmployeeType.SALARIED_COMMISION;


@Service
public class Application implements ApplicationEventPublisherAware {

    @Autowired
    private final Repository employeeRepository;
    private ApplicationEventPublisher eventPublisher;

    public Application(Repository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }
    public void createEmployee(Employee e) {
        employeeRepository.save(e);
    }
    public List<Employee> findAllEmplType(Employee.EmployeeType type) {
        return employeeRepository.findAllByEmplType(type);
    }



    public List<String> getTotalSalaryByType(Employee.EmployeeType type) {
        List<String> list = new ArrayList<>();

        for (Employee e : employeeRepository.findAllByEmplType(type)) {
            switch (type) {
                case SALARIED:
                    list.add("Name: " + e.getName() + ", type: " + e.getEmplType() + ", salary: " + e.getFixedSalary());
                    break;
                case HOURLY:
                    list.add("Name: " + e.getName() + ", type: " + e.getEmplType() + ", salary: " + calcHourly(e));
                    break;
                case COMMISION:
                    list.add("Name: " + e.getName() + ", type: " + e.getEmplType() + ", salary: " + calcCommission(e));
                    break;
                case SALARIED_COMMISION:
                    list.add("Name: " + e.getName() + ", type: " + e.getEmplType() + ", salary: " + calcSalaryComm(e));
                    break;
            }
        }
        return list;
    }

    private Double calcHourly(Employee e) {
        Double salary = 0.0;
        if (e.getHoursWorked() > 40) {
            salary += (e.getHoursWorked()-40)* e.getHourRate() * 1.5 + 40 * e.getHourRate() ;
        } else {
            salary = e.getHourRate() * e.getHoursWorked();
        }
        return salary;
    }

    private Double calcCommission(Employee e) {
        Double salary = e.getSales() * Double.parseDouble(Float.toString(e.getCommRate())) ;
        return salary;
    }

    private Double calcSalaryComm(Employee e) {
        Double salary = e.getFixedSalary() + e.getSales()*Double.parseDouble(Float.toString(e.getCommRate())) ;
        return salary;
    }

    public void increaseFixedSalaryByType(Employee.EmployeeType type, int percent) {

        if (type.equals(SALARIED)) {
            for (Employee e : employeeRepository.findAllByEmplType(type)) {
                Double salary = e.getFixedSalary() * percent/100 +  e.getFixedSalary();
                e.setFixedSalary(salary);
                employeeRepository.save(e);
                this.eventPublisher.publishEvent(new SalaryIncreseEvent(this, e));
            }
        }
        else if(type.equals(SALARIED_COMMISION)){
            for (Employee e : employeeRepository.findAllByEmplType(type)) {
                Float salary = e.getCommRate() + Float.parseFloat(Double.toString(e.getCommRate() + percent/100 ));
                e.setCommRate(salary);
                employeeRepository.save(e);
                this.eventPublisher.publishEvent(new SalaryIncreseEvent(this, e));
            }
        }

    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.eventPublisher = applicationEventPublisher;
    }
}
