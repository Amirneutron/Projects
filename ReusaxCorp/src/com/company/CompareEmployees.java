package com.company;

import java.util.Comparator;

public abstract class CompareEmployees implements Comparator<Employee> {

    /**
     * Sort employees list based on name
     */
    protected static Comparator<Employee> compareByName = new Comparator<Employee>() {

        @Override
        public int compare(Employee employee1, Employee employee2) {
            return employee1.getName().compareTo(employee2.getName());
        }
    };


    /**
     * Sort employees list based on net salary
     */
    protected static Comparator<Employee> compareByNetSalary = new Comparator<Employee>() {

        @Override
        public int compare(Employee employee1, Employee employee2) {
            return (int) (employee1.getNetSalary() - employee2.getNetSalary());
        }
    };
}