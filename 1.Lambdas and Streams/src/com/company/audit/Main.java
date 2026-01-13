package com.company.audit;
import com.company.audit.Employee;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static void main() {
        List<Employee>e = Employee.getSampleData();

        List<Employee>finalemp = e.stream()
                .filter(salary -> salary.getSalary() > 70000)
                .filter(department -> department.getDepartment().equals("ENGINEERING"))
                .toList();
        System.out.println(finalemp);

        List<String> standardName = e.stream()
                .map(emp -> emp.getName().toUpperCase())
                .toList();
        System.out.println(standardName);

        double totalBudget = e.stream()
                .mapToDouble(Employee::getSalary)
                .sum();
        System.out.println(totalBudget);


    }
}
