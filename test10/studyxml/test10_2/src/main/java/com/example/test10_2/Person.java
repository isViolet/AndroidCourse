package com.example.test10_2;

import androidx.annotation.NonNull;

public class Person {
    String name,age;
    int id;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @NonNull
    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age="+ age + "]";
    }
}
