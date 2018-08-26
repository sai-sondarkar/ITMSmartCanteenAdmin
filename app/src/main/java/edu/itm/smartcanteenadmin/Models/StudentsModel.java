package edu.itm.smartcanteenadmin.Models;

/**
 * Created by sai on 18/7/18.
 */

public class StudentsModel {

    public String name;
    public String rollNumber;
    public int packaage;

    public StudentsModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getPackaage() {
        return packaage;
    }

    public void setPackaage(int packaage) {
        this.packaage = packaage;
    }
}
