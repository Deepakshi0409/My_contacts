package com.example.mycontacts;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")
public class contact {

    @ColumnInfo(name = "Name")
    private String taskName;
    @NonNull()
    @PrimaryKey()
    @ColumnInfo(name = "Phone")
    private String taskPhone;
    @ColumnInfo(name = "Email")
    private String taskEmail;
    @ColumnInfo(name = "Age")
    private String taskAge;
    @ColumnInfo(name = "City")
    private String taskCity;
    @ColumnInfo(name = "College")
    private String taskCollege;

    public contact(String taskName, String taskPhone, String taskEmail, String taskAge, String taskCity, String taskCollege) {
        this.taskName = taskName;
        this.taskPhone = taskPhone;
        this.taskEmail = taskEmail;
        this.taskAge = taskAge;
        this.taskCity = taskCity;
        this.taskCollege = taskCollege;
    }
    public contact(){

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskPhone() {
        return taskPhone;
    }

    public void setTaskPhone(String taskPhone) {
        this.taskPhone = taskPhone;
    }

    public String getTaskEmail() {
        return taskEmail;
    }

    public void setTaskEmail(String taskEmail) {
        this.taskEmail = taskEmail;
    }

    public String getTaskAge() {
        return taskAge;
    }

    public void setTaskAge(String taskAge) {
        this.taskAge = taskAge;
    }

    public String getTaskCity() {
        return taskCity;
    }

    public void setTaskCity(String taskCity) {
        this.taskCity = taskCity;
    }

    public String getTaskCollege() {
        return taskCollege;
    }

    public void setTaskCollege(String taskCollege) {
        this.taskCollege = taskCollege;
    }

    public boolean isContactEqual(contact t2) {
        return ((taskName.equals(t2.getTaskName())) && (taskPhone.equals(t2.getTaskPhone())) && (taskEmail == t2.getTaskEmail()) && (taskAge.equals(t2.getTaskAge())) && (taskCity.equals(t2.getTaskCity())) && (taskCollege.equals(t2.getTaskCollege())));
    }
}
