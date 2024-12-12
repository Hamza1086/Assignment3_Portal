package com.example.portal;

import java.io.Serializable;

public class Student implements Serializable {
    String firstName;
    String username;
    String password;
    String RegNo;
    int sem;
    long fees;
    static int counter = 0;

    public Student(String firstName, String username, String password, int sem, long fees) {
        this.RegNo = String.format("SP24-BSE-%03d", ++counter);
        this.firstName = firstName;
        this.username = username;
        this.password = password;
        this.sem = sem;
        this.fees = fees;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public long getFees() {
        return fees;
    }

    public String getRegNo() {
        return RegNo;
    }

    public void setFees(long fees) {
        this.fees = fees;
    }

    @Override
    public String toString() {
        return "ID: " + RegNo + ", FirstName: " + firstName + ", Username: " + username + ", Password: " + password + ", Sem: " + sem + ", Fee: " + fees;
    }
}
