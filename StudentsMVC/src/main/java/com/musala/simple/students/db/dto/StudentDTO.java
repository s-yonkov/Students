package com.musala.simple.students.db.dto;

import org.springframework.stereotype.Component;

@Component
public class StudentDTO {

    private long id;
    private String name;
    private int age;
    private double grade;

    private StudentDTO() {
    }

    private StudentDTO(StudentDTOBuilder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.age = builder.age;
        this.grade = builder.grade;
    }

    public static class StudentDTOBuilder {

        private long id;
        private String name;
        private int age;
        private double grade;

        public StudentDTOBuilder(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public StudentDTOBuilder age(int age) {
            this.age = age;
            return this;
        }

        public StudentDTOBuilder grade(double grade) {
            this.grade = grade;
            return this;
        }

        public StudentDTO build() {
            return new StudentDTO(this);
        }

    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getGrade() {
        return grade;
    }

    @Override
    public String toString() {
        return String.format("Id: %d, Name: %s, age: %d, grade: %f ", this.id, this.name, this.age, this.grade);

    }

}
