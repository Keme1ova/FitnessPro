package com.example.fitnespro;
import java.util.Date;

public class Payments {
    int id_payment;
    String lastname;
    String name;
    String membership;
    String amount;
    String paid;
    String status;


    public Payments(int id_payment, String lastname, String name, String membership, String amount, String paid, String status) {
        this.id_payment = id_payment;
        this.lastname = lastname;
        this.name = name;
        this.membership = membership;
        this.amount = amount;
        this.paid = paid;
        this.status = status;

    }


    public int getId_payment() {
        return id_payment;
    }

    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaid() {
        return paid;
    }

    public void setPaid(String paid) {
        this.paid = paid;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
