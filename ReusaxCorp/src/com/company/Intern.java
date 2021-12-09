package com.company;

public class Intern extends Employee {

    // private instance variable
    private int gpa;

    // constant for the Intern
    private static final int MIN_GPA = 0;
    private static final int MAX_GPA = 10;
    private static final int BONUS_FOR_GPA = 1000;
    private static final int GPA_LOWER_LIMIT = 5;
    private static final int GPA_UPPER_LIMIT = 8;


    // initialize instance variables
    public Intern(String id, String name, double grossSalary, int gpa) {
        super(id, name, grossSalary);

        // only accept GPA between range 0-10
        if (gpa < MIN_GPA) {
            this.gpa = MIN_GPA;
        } else if (gpa > MAX_GPA) {
            this.gpa = MAX_GPA;
        } else {
            this.gpa = gpa;
        }
    }


    /**
     * Change Intern's GPA
     * @param gpa New value for GPA
     */
    protected void setGpa(int gpa) {
        this.gpa = gpa;
    }


    /**
     * Return gross salary as per gpa
     * @return double
     */
    protected double getGrossSalary() {
        if (gpa >= GPA_LOWER_LIMIT && gpa < GPA_UPPER_LIMIT) {
            return super.getGrossSalary();
        } else if (gpa >= GPA_UPPER_LIMIT) {
            return super.getGrossSalary() + BONUS_FOR_GPA;
        } else {
            return 0;
        }
    }


    /**
     * Return net salary
     * @return double
     */
    protected double getNetSalary() {
        return this.getGrossSalary();
    }


    /**
     * A String representation of Intern's object
     * @return String
     */
    public String toString() {
        return "ID: " + getId() + " (Position: Intern)" + END_OF_LINE +
                "Name: " + getName() + END_OF_LINE +
                "Gross Salary: " + this.getGrossSalary() + " SEK" + END_OF_LINE +
                "Net Salary: " + this.getNetSalary() + " SEK" + END_OF_LINE +
                "GPA: " + this.gpa;
    }
}

