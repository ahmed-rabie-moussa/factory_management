
package factorysystem;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {
    private final SimpleIntegerProperty userID;
    private final SimpleStringProperty userName;
    private final SimpleStringProperty userpassword;
    private final SimpleStringProperty address;
    private final SimpleDoubleProperty hoursWorked;
    private final SimpleIntegerProperty sectionID;
    private final SimpleDoubleProperty rating;
    private final SimpleIntegerProperty phoneNumber;
    private final SimpleDoubleProperty salary;
    private final SimpleStringProperty JobID;

    public User(int userID, String userName, String userpassword, String address, double hoursWorked, int sectionID, double rating, int phoneNumber, double salary, String JobID) {
        this.userID = new SimpleIntegerProperty(userID);
        this.userName = new SimpleStringProperty(userName);
        this.userpassword = new SimpleStringProperty(userpassword);
        this.address = new SimpleStringProperty(address);
        this.hoursWorked = new SimpleDoubleProperty(hoursWorked);
        this.sectionID = new SimpleIntegerProperty(sectionID);
        this.rating = new SimpleDoubleProperty(rating);
        this.phoneNumber = new SimpleIntegerProperty(phoneNumber);
        this.salary = new SimpleDoubleProperty(salary);
        this.JobID = new SimpleStringProperty(JobID);
    }

    public int getUserID() {
        return userID.get();
    }

    public void setUserID(int userID) {
        this.userID.set(userID);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName.set(userName);
    }

    public String getUserpassword() {
        return userpassword.get();
    }

    public void setUserpassword(String userpassword) {
        this.userpassword.set(userpassword);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public double getHoursWorked() {
        return hoursWorked.get();
    }

    public void setHoursWorked(double hoursWorked) {
        this.hoursWorked.set(hoursWorked);
    }

    public int getSectionID() {
        return sectionID.get();
    }

    public void setSectionID(int sectionID) {
        this.sectionID.set(sectionID);
    }

    public double getRating() {
        return rating.get();
    }

    public void setRating(double rating) {
        this.rating.set(rating);
    }

    public int getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(int phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public double getSalary() {
        return salary.get();
    }

    public void setSalary(double salary) {
        this.salary.set(salary);
    }

    public String getJobID() {
        return JobID.get();
    }

    public void setJobID(String JobID) {
        this.JobID.set(JobID);
    }
    
}
