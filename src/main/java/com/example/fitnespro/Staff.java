package com.example.fitnespro;
import java.util.Date;

public class Staff {
    String fullname;
    String phone_number;
    String email;
    String job;


    public Staff(String fullname, String phone_number, String email, String job) {
        this.fullname = fullname;
        this.phone_number = phone_number;
        this.email = email;
        this.job = job;

    }


    public String getFullname() {
        return fullname;
    }
    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

}