package com.musala.simple.students.db;

import org.springframework.stereotype.Component;

@Component
public class StudentDTO {

    private long id;
    private String name;
    private int age;
    private double grade;

    public StudentDTO() {
    }

    public StudentDTO(long id, String name, int age, double grade) {
        this();
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

    public long getId() {
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, age: %d, grade: %f ", this.id, this.name, this.age, this.grade);

    }

}
