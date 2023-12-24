package com.example.fitnespro;
import java.util.Date;

public class Members {
    int id_mem;
    String lastname;
    String name;
    String contact;
    String group_member;
    String membership;
    String trainer;
    String start_ab;
    String end_ab;

    public Members(int id_mem, String lastname, String name, String contact, String group_member, String membership, String trainer, String start_ab, String end_ab) {
        this.id_mem = id_mem;
        this.lastname = lastname;
        this.name = name;
        this.contact = contact;
        this.group_member = group_member;
        this.membership = membership;
        this.trainer = trainer;
        this.start_ab = start_ab;
        this.end_ab = end_ab;
    }



    public int getId_mem() {
        return id_mem;
    }

    public void setId_mem(int id_mem) {
        this.id_mem = id_mem;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGroup_member() {
        return group_member;
    }

    public void setGroup_member(String group_member) {
        this.group_member = group_member;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getStart_ab() {
        return start_ab;
    }

    public void setStart_ab(String start_ab) {
        this.start_ab = start_ab;
    }

    public String getEnd_ab() {
        return end_ab;
    }

    public void setEnd_ab(String end_ab) {
        this.end_ab = end_ab;
    }
}
