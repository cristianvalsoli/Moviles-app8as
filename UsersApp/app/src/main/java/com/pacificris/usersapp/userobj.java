package com.pacificris.usersapp;

public class userobj {
    String Firsname;
    String Lastname;
    String Username;
    String Password;
    String Birth;
    String Country;
    String Phone;
    String gender;

    public userobj(String firsname, String lastname, String username, String password, String birth, String country, String phone, String gender) {
        Firsname = firsname;
        Lastname = lastname;
        Username = username;
        Password = password;
        Birth = birth;
        Country = country;
        Phone = phone;
        this.gender = gender;
    }

    public String getFirsname() {
        return Firsname;
    }

    public void setFirsname(String firsname) {
        Firsname = firsname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String birth) {
        Birth = birth;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
