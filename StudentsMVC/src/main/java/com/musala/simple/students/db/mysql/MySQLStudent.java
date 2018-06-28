package com.musala.simple.students.db.mysql;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class MySQLStudent {

    @Id
    private long id;
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    @NotNull
    @Size(min = 12, max = 99)
    private int age;
    @NotNull
    @Size(min = 2, max = 6)
    private double grade;

    public MySQLStudent() {
    }

    public MySQLStudent(long id, String name, int age, double grade) {
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
