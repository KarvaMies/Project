package com.example.harkka_login;

import java.util.Date;

public class User {
    private String email, password, firstName, lastName, homeTown, birthDate;

    public User(String email, String password, String firstName, String lastName, String birthDate, String hometown) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.homeTown = hometown;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getHomeTown() {
        return homeTown;
    }
}
