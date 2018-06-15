package com.musala.simple.students.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    MongoStudentDAO mongoDB;
    @Autowired
    MySQLStudentDAO mySQLDB;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        mySQLDB.insertStudent(new Student(1, "Angel", 22, 3));
        // save a couple of customers
        // mongoDB.repository.save(new Student(1, "Angel", 22, 3));
        mySQLDB.insertStudent(new Student(4, "Pesho", 22, 3));
        //
        // // fetch all customers
        // System.out.println("Customers found with findAll():");
        // System.out.println("-------------------------------");
        //
        for (Student student : mongoDB.getStudents().getStudents()) {
            student.printInfo();
        }
        
        for (Student student : mySQLDB.getStudents().getStudents()) {
            student.printInfo();
        }
        System.out.println("asd");

    }

}