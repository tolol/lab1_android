package com.lab1.phone_book;

import java.io.Serializable;

public class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String phone;
    private String email;
    private Integer id;

    Person(String firstname_, String lastname_, String phone_, String email_, Integer id_)
    {
        this.firstname = firstname_;
        this.lastname = lastname_;
        this.phone = phone_;
        this.email = email_;
        this.id = id_;
    }

    public Integer getId(){
        return this.id;
    }

    public String getFirstname(){
        return this.firstname;
    }

    public void setFirstname(String firstname){
        this.firstname = firstname;
    }

    public String getLastname(){
        return this.lastname;
    }

    public void setLastname(String lastname){
        this.lastname = lastname;
    }

    public String getPhone(){
        return this.phone;
    }

    public void setPhone(String phone){
        this.phone = phone;
    }

    public String getEmail(){
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }

}
