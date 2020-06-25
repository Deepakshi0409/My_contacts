package com.example.mycontacts;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "contact")

public class contact {
    @ColumnInfo(name = "Name")
    private String taskName;
    @PrimaryKey()
    @ColumnInfo(name = "Phone")
    private int taskPhone;
    @ColumnInfo(name = "Email")
    private String taskEmail;
    @ColumnInfo(name = "Age")
    private int taskAge;
    @ColumnInfo(name = "City")
    private String taskCity;
    @ColumnInfo(name = "College")
    private String taskCollege;

    public contact(String taskName, int taskPhone, String taskEmail, int taskAge, String taskCity, String taskCollege) {
        this.taskName = taskName;
        this.taskPhone = taskPhone;
        this.taskEmail = taskEmail;
        this.taskAge = taskAge;
        this.taskCity = taskCity;
        this.taskCollege = taskCollege;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public int getTaskPhone() {
        return taskPhone;
    }

    public void setTaskPhone(int taskPhone) {
        this.taskPhone = taskPhone;
    }

    public String getTaskEmail() {
        return taskEmail;
    }

    public void setTaskEmail(String taskEmail) {
        this.taskEmail = taskEmail;
    }
    public int getTaskAge ( ){
        return taskAge;
    }
    public void setTaskAge(int taskAge){
        this.taskAge = taskAge;
    }
    public String getTaskCity(){
        return taskCity;
    }
    public void setTaskCity(String taskCity){
        this.taskCity = taskCity;
    }
    public String getTaskCollege(){
        return taskCollege;
    }
    public void setTaskCollege(String taskCollege){
        this.taskCollege = taskCollege;
    }

}
