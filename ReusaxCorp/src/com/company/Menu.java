package com.company;

public class Menu {

    //constants for the menu
    private final String REGISTER_NEW_EMPLOYEE = "1";
    private final String REMOVE_EMPLOYEE = "2";
    private final String RETRIEVE_EMPLOYEE_INFO = "3";
    private final String UPDATE_EMPLOYEE_INFO = "4";
    private final String CALCULATE_TOTAL_GROSS_SALARIES = "5";
    private final String CALCULATE_TOTAL_NET_SALARIES = "6";
    private final String PRINT_TOTAL_REGISTERED = "7";
    private final String PROMOTE_TO_EMPLOYEE = "8";
    private final String PROMOTE_TO_INTERN = "9";
    private final String PROMOTE_TO_MANAGER = "10";
    private final String PROMOTE_TO_DIRECTOR = "11";
    private final String QUIT = "12";

    //declaring reference
    ReusaxCorp dataBase = new ReusaxCorp();

    //Constants for declaring employee type menu
    private final String NORMAL_EMPLOYEE = "1";
    private final String DIRECTOR = "2";
    private final String MANAGER = "3";
    private final String INTERN = "4";

    //constant error messages
    private static final String END_OF_LINE = System.lineSeparator();
    private static final String INVALID_INPUT = "Invalid input, please type suitable inputs" + END_OF_LINE + "press enter to continue";
    private static final String LIST_IS_EMPTY = "PLease register employees before accessing this service" + END_OF_LINE + "press enter to continue";
    private static final String DUPLICATE_ID = "Error : duplicate ID detected";

    public static void errorMessage(String erorrMessage) {
        System.out.println(erorrMessage);
        IO.eatLine();
    }


    public static String printMainMenu() {
        System.out.println("Please choose one of the following options");
        System.out.println("******************************");
        System.out.println("1. Register an employee");
        System.out.println("2. Remove an employee");
        System.out.println("3. Retrieve an employee info");
        System.out.println("4. Update an employee info");
        System.out.println("5. Calculate the total gross salaries payed");
        System.out.println("6. Calculate the total net salaries payed");
        System.out.println("7. Show the total number of employees registered");
        System.out.println("8. Quit the program");
        System.out.println("******************************");
        String option = IO.readLine();
        return option;
    }


    public void getMenu(){

        String option = "";

        while (!option.equals(QUIT)) {
            String status;
            String ID;
            String degree;
            String department;
            String name = "";
            double numStatus;
            double grossSalary = 1;
            option = Menu.printMainMenu();

            int gpa;

            switch (option){

                case REGISTER_NEW_EMPLOYEE:
                    status = newEmployee();
                    if (status.equals("erorr"))
                        errorMessage(DUPLICATE_ID);
                    System.out.println(status);
                    break;

                case REMOVE_EMPLOYEE:
                    ID = findID();
                    status = dataBase.removeEmployee(ID);
                    if (status.equals("empty"))
                        System.out.println(LIST_IS_EMPTY);
                    else if (status.equals("erorr"))
                        System.out.println(INVALID_INPUT);
                    else
                        System.out.println(status);
                    break;

                case RETRIEVE_EMPLOYEE_INFO:
                    ID = findID();
                    status = dataBase.retrieveEmployee(ID);
                    if (status.equals("empty"))
                        errorMessage(LIST_IS_EMPTY);
                    else if (status.equals("erorr"))
                        errorMessage(INVALID_INPUT);
                    else
                        System.out.println(status);
                    break;

                case UPDATE_EMPLOYEE_INFO:
                    ID = findID();
                    int whatToUpdate = updateMinorInfo();
                    if (whatToUpdate == 1)
                        name = getName();
                    if (whatToUpdate == 2)
                        grossSalary = getGrossSalary();
                    dataBase.updateEmployee(ID, whatToUpdate, name, grossSalary);
                    break;

                case CALCULATE_TOTAL_GROSS_SALARIES:
                    numStatus = dataBase.calculateTotalGross();
                    if (numStatus == -1)
                        errorMessage(LIST_IS_EMPTY);
                    else
                        System.out.println("The total amount of gross salaries paid by ReusaxCorp is " + numStatus);
                    break;

                case CALCULATE_TOTAL_NET_SALARIES:
                    numStatus = dataBase.calculateTotalNet();
                    if (numStatus == -1)
                        errorMessage(LIST_IS_EMPTY);
                    else
                        System.out.println("The total amount of net salaries paid by ReusaxCorp is " + numStatus);
                    break;

                case PRINT_TOTAL_REGISTERED:
                    numStatus = dataBase.totalRegisteredEmployees();
                    System.out.println("The number of employees registered in the system is " + numStatus);
                    break;

                case PROMOTE_TO_EMPLOYEE:
                    ID = findID();
                    dataBase.promoteToEmployee(ID);
                    break;

                case PROMOTE_TO_INTERN:
                    ID = findID();
                    gpa = getGPA();
                    dataBase.promoteToIntern(ID, gpa);
                    break;

                case PROMOTE_TO_MANAGER:
                    ID = findID();
                    degree = getDegree();
                    dataBase.promoteToManager(ID, degree);
                    break;

                case PROMOTE_TO_DIRECTOR:
                    ID = findID();
                    degree = getDegree();
                    department = getDepartment();
                    dataBase.promoteToDirector(ID, degree, department);
                    break;

                case QUIT:
                    System.out.println("Thank you for using this service");
                    break;

                default:
                    Menu.errorMessage(INVALID_INPUT);
            }
        }
    }


    public static int updateMinorInfo() {
        System.out.println("choose on of the options to change");
        System.out.println("1. name");
        System.out.println("2. gross salary");
        return IO.readINT();
    }


    public String employeeChoiceMenu() {
        System.out.println("Please choose the occupation of the employee:");
        System.out.println("1. Production worker (normal employee)");
        System.out.println("2. Director");
        System.out.println("3. Manager");
        System.out.println("4. A student doing an intern");
        return IO.readLine();
    }


    public String newEmployee() {
        System.out.print("Please type in the Employee ID: ");
        String ID = IO.readLine();

        System.out.print("Please type in the Employees name: ");
        String name = IO.readLine();
        System.out.print("Please type in the Employees gross salary: ");
        double grossSalary = IO.readDouble();

        String choice = employeeChoiceMenu();
        String confirmCreation;
        String degree;
        switch (choice) {
            case NORMAL_EMPLOYEE:
                confirmCreation = dataBase.newEmployee(ID, name, grossSalary);
                break;

            case DIRECTOR:
                System.out.print("Please enter the manager degree: ");
                degree = IO.readLine();
                System.out.print("Please enter the manager department: ");
                String department = IO.readLine();
                confirmCreation = dataBase.newDirector(ID, name, grossSalary, degree, department);
                break;

            case MANAGER:
                System.out.print("Please enter the manager degree: ");
                degree = IO.readLine();
                confirmCreation = dataBase.newManager(ID, name, grossSalary, degree);
                break;

            case INTERN:
                System.out.print("Please type in the student GPA: ");
                int GPA = IO.readINT();
                confirmCreation = dataBase.newIntern(ID, name, grossSalary, GPA);
                break;
            default:
                Menu.errorMessage(INVALID_INPUT);
                confirmCreation = "error when creating employee";
                break;

        }
        return confirmCreation;
    }


    public static String findID() {
        System.out.print("Please enter the ID of the desired employee: ");
        return IO.readLine();
    }


    public static String getName() {
        System.out.print("Enter the name: ");
        return IO.readLine();
    }


    public static double getGrossSalary() {
        System.out.print("Please enter the gross Salary: ");
        return IO.readDouble();
    }


    public static String getDegree() {
        System.out.print("Please type in the new degree: ");
        return IO.readLine();
    }


    public static int getGPA() {
        System.out.print("Please insert an new GPA value to update: ");
        return IO.readINT();
    }

    public static String getDepartment() {
        System.out.print("Please type in the Department of the director to update: ");
        return IO.readLine();
    }


    public static void main(String[] args) {
        Menu mainMenu = new Menu();
        System.out.println("******************************");
        System.out.println("   Welcome to ReusaxCorp gorup   ");
        System.out.println("******************************");
        mainMenu.getMenu();
    }
}


