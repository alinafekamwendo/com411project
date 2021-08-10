package com.example.navigationdrawer.loginRegister;

public class RegisteredUsers {

    public String firstname,
            surname,
            email,
            password;

    public RegisteredUsers(){

    }
    public RegisteredUsers(String firstname, String surname, String email, String password){
        this.firstname=firstname;
        this.surname=surname;
        this.email=email;
        this.password=password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPassword() {
        return password;
    }

    public String getSurname() {
        return surname;
    }
}
