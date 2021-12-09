package com.company;

import java.util.ArrayList;

public class ReusaxCorp {

    //constant error messages
    public static final String END_OF_LINE = System.lineSeparator();
    public static final String INVALID_INPUT = "Invalid input, please type suitable inputs" + END_OF_LINE;
    public static final String LIST_IS_EMPTY = "PLease register employees before accessing this service" + END_OF_LINE;
    public static final String INVALID_ID = "Error : invalid ID detected" + END_OF_LINE + "press enter to continue";
    public static final String MATCHING_EMPLOYEE_TYPES = "Error the employee ocupation is identical to the occupation refered to in the update" + END_OF_LINE;


    //Attribute for the class Reusax
    public ArrayList <Employee> employeesList;
    // benefit for Director
    private static double benefit = 5000;


    public ReusaxCorp() {
        this.employeesList = new ArrayList<>();
    }


    public String newEmployee(String ID, String name, double grossSalary) {
        boolean flag = IdDuplicateChecker(ID);
        if(! (employeesList.isEmpty()))
            if ( flag )
                return INVALID_ID;
        Employee newEmployee = new Employee(ID, name, grossSalary);
        employeesList.add(newEmployee);
        return "Employee is successfully created";
    }


    public String newDirector(String ID, String name, double grossSalary, String degree, String department) {
        boolean flag = IdDuplicateChecker(ID);
        if(! (employeesList.isEmpty()))
            if ( flag )
                return INVALID_ID;
        Employee newDirector = new Director(ID, name, grossSalary, degree, department);
        employeesList.add(newDirector);
        return "Director successfully created";
    }


    public String newManager(String ID, String name, double grossSalary, String degree) {
        boolean flag = IdDuplicateChecker(ID);
        if(! (employeesList.isEmpty()))
            if ( flag )
                return INVALID_ID;
        Employee newManager = new Manager(ID, name, grossSalary, degree);
        employeesList.add(newManager);
        return "Manager is successfully created";
    }


    public String newIntern(String ID, String name, double grossSalary, int GPA) {
        boolean flag = IdDuplicateChecker(ID);
        if(! (employeesList.isEmpty()))
            if ( flag )
                return INVALID_ID;
        Employee newIntern = new Intern(name, ID, grossSalary, GPA);
        employeesList.add(newIntern);
        return "Intern student successfully created";
    }


    public Employee findEmployee(String ID) {
        for (Employee foundEmployee: employeesList) {
            if ( foundEmployee.getId().equals(ID))
                return foundEmployee;
        }
        return null;
    }

    public String removeEmployee(String ID) {
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (! IdDuplicateChecker(ID))
            return INVALID_ID;
        Employee employee = findEmployee(ID);
        employeesList.remove(employee);
        return "Employee removed";
    }


    public String retrieveEmployee(String ID) {
        Employee employee = null;
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (! IdDuplicateChecker(ID))
            return INVALID_ID;
        for (Employee foundEmployee : employeesList)
            if (foundEmployee.getId().equals(ID))
                employee = foundEmployee;
        return employee.toString();
    }


    public String updateEmployee(String ID, int whatToUpdate, String name, double grossSalary) {
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (! IdDuplicateChecker(ID))
            return INVALID_ID;
        Employee employee = findEmployee(ID);
        if (whatToUpdate == 1)
        {
            employee.setName(name);
            return "success";
        }
        if (whatToUpdate == 2) {
            employee.setGrossSalary(grossSalary);
            return "success";
        }
        return INVALID_INPUT;
    }

    public double calculateTotalGross() {
        if (checkIfListIsEmpty())
            return -1;
        double sumOfGross = 0;
        for (Employee anEmployee : employeesList) {
            sumOfGross += anEmployee.getGrossSalary();
        }
        return sumOfGross;
    }

    public double calculateTotalNet() {
        if (checkIfListIsEmpty()) {
            return -1;
        }
        double sumOfNet = 0;
        for (Employee anEmployee : employeesList) {
            sumOfNet += anEmployee.getNetSalary();
        }
        return sumOfNet;
    }

    public String promoteToEmployee(String ID) {

        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (! IdDuplicateChecker(ID))
            return INVALID_ID;
        Employee employee = findEmployee(ID);
        if (!(((employee instanceof Intern))|| (employee instanceof Manager)))
            return "not updatable";
        String name = employee.getName();
        double grossSalary = employee.getGrossSalary();
        Employee newEmployee = new Employee(ID, name, grossSalary);
        employeesList.remove(employee);
        employeesList.add(newEmployee);

        return "success";
    }

    public String promoteToIntern(String ID, int gpa) {
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
//    if ( ! IdDuplicateChecker(ID))
//      return INVALID_ID;
        Employee employee = findEmployee(ID);
        String currentEmployeeType = determineEmployeeType(employee);
        if (currentEmployeeType.equals("Intern"))
            return MATCHING_EMPLOYEE_TYPES;
        String name = employee.getName();
        double grossSalary = employee.getGrossSalary();
        Employee newintern = new Intern(ID, name, grossSalary, gpa);
        employeesList.add(newintern);
        employeesList.remove(employee);
        return "success";
    }

    public String promoteToManager(String ID, String degree) {
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (!IdDuplicateChecker(ID))
            return INVALID_ID;
        Employee employee = findEmployee(ID);
        String currentEmployeeType = determineEmployeeType(employee);
        if (currentEmployeeType.equals("Manager"))
            return MATCHING_EMPLOYEE_TYPES;

        Employee newManager;
        String name = employee.getName();
        double grossSalary = employee.getGrossSalary();
        newManager = new Manager(ID, name, grossSalary, degree);
        employeesList.add(newManager);
        employeesList.remove(employee);
        return "success";
    }

    public String promoteToDirector(String ID, String degree, String department) {
        if (checkIfListIsEmpty())
            return LIST_IS_EMPTY;
        if (! IdDuplicateChecker(ID))
            return INVALID_ID;
        Employee employee = findEmployee(ID);
        String currentEmployeeType = determineEmployeeType(employee);
        if (currentEmployeeType.equals("Director"))
            return MATCHING_EMPLOYEE_TYPES;
        String name = employee.getName();
        double grossSalary = employee.getGrossSalary();
        Employee newDirector = new Director(ID, name, grossSalary, degree, department);
        employeesList.add(newDirector);
        employeesList.remove(employee);
        return "success";
    }

    public int totalRegisteredEmployees() {
        return employeesList.size();
    }


    protected String sortBy(String sortBy, String sortOrder) {

        if (sortBy.equalsIgnoreCase("name") && sortOrder.equalsIgnoreCase("ascending")) {
            employeesList.sort(CompareEmployees.compareByName);
        }
        if (sortBy.equalsIgnoreCase("name") && sortOrder.equalsIgnoreCase("descending")) {
            employeesList.sort(CompareEmployees.compareByName.reversed());
        }
        if (sortBy.equalsIgnoreCase("net salary") && sortOrder.equalsIgnoreCase("ascending")) {
            employeesList.sort(CompareEmployees.compareByNetSalary);
        }
        if (sortBy.equalsIgnoreCase("net salary") && sortOrder.equalsIgnoreCase("descending")) {
            employeesList.sort(CompareEmployees.compareByNetSalary.reversed());
        }

        return "success";
    }


    public static double getBenefit() {
        return benefit;
    }


    public String setBenefit(double newBenefit) {
        if (checkIfListIsEmpty()) {
            return LIST_IS_EMPTY;
        }
        benefit = newBenefit;
        return "success";
    }


    public boolean checkIfListIsEmpty() {
        boolean checker;
        if (employeesList.isEmpty()) {
            checker = true;
            return checker;
        } else {
            checker = false;
        }
        return checker;
    }


    public String determineEmployeeType(Employee foundEmployee) {
        if (foundEmployee instanceof Director)
            return "Director";
        if (foundEmployee instanceof Manager)
            return "Manager";
        if (foundEmployee instanceof Intern)
            return "Intern";
        return "normal employee";
    }

    public boolean IdDuplicateChecker(String id) {
        for (Employee foundEmployee: employeesList) {
            if ( foundEmployee.getId().equals(id))
                return true;
        }
        return false;
    }


    public String getList() {
        if (checkIfListIsEmpty()) {
            return LIST_IS_EMPTY;
        } else  {
            return employeesList.toString();
        }
    }
}