package com.example.studyxml;

import androidx.annotation.NonNull;

public class Person {
    public Person(String name, String pwd){
        this.name = name;
        this.pwd = pwd;
    }

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    String pwd;

    @NonNull
    @Override
    public String toString() {
        String str = "Person{" + "name='" + name + "\'" + "pwd='" + pwd + "\'" + '}';
        return str;
    }
}
