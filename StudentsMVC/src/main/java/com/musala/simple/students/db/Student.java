package com.musala.simple.students.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.persistence.Entity;


@Entity
public class Student {
    
    final static Logger slf4jLogger = LoggerFactory.getLogger(Student.class);

    private int id;
    private String name;
    private int age;
    private int grade;
    
    

    protected Student() {

    }

    public Student(int id, String name, int age, int grade) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.grade = grade;
    }

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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void printInfo() {
        slf4jLogger.info("Id: {}, Name: {}, age: {}, grade: {} \n", this.id, this.name, this.age, this.grade);
    }

}
