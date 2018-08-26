package edu.itm.smartcanteenadmin.Models;

/**
 * Created by sai on 18/7/18.
 */

public class CanteenServiceModel {

    public String studentName;
    public String rollNumber;
    public String serviceType;
    public long timeStamp;

    public CanteenServiceModel() {
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
