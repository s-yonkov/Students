package com.musala.simple.students.db.mongo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MongoStudent {

    @Id
    private long id;
    private String name;
    private int age;
    private double grade;

    public MongoStudent() {

    }

    public MongoStudent(long id, String name, int age, double grade) {
        super();
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
}
