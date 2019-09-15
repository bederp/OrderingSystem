package it.slawekpaciorek.model;

import java.time.LocalDateTime;

public class User {

    String name;
    String lastName;
    String idNumber;
    String email;

    public User(){
        this.idNumber = generateRandomUniquIDNumber(this);
    }

    private String generateRandomUniquIDNumber(Object object){
        return String.valueOf(object.hashCode()) + this.name.substring(0,1) + this.lastName.substring(0,1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
