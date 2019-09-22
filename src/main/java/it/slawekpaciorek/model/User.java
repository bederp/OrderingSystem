package it.slawekpaciorek.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

import static it.slawekpaciorek.repo.InMemoryDB.getUsers;

public class User {

    Logger logger = LoggerFactory.getLogger(User.class);

    String name;
    String lastName;
    int idNumber;
    String email;

    public User(){

        idNumber = getUsers().size() + 1;
        logger.info("Creating USER with id number : " + idNumber);
    }

    public User(String name, String lastName, String email) {
        this.name = name;
        this.lastName = lastName;
        this.idNumber = getUsers().size() + 1;
        this.email = email;

        logger.info("Creating USER with id number : " + idNumber);
    }

    public User(int userId) {
        this.idNumber = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return name.equals(user.name) &&
                lastName.equals(user.lastName) &&
                idNumber == (user.idNumber) &&
                email.equals(user.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName, idNumber, email);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", idNumber='" + idNumber + '\'' +
                ", email='" + email + '\'' +
                '}';
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

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}

