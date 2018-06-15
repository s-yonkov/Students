package com.musala.simple.students.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;

public class StudentModel {

    final static Logger LOGGER = LoggerFactory.getLogger(StudentModel.class);
    private static Gson gson = new Gson();

    protected MongoStudentDAO mongo;
    protected MySQLStudentDAO mysql;

    public void writeInDB(String path, StudentDAO db) throws FileNotFoundException {
        if (fileExists(path)) {
            BufferedReader br = new BufferedReader(new FileReader(new File(path)));
            StudentGroup students = gson.fromJson(br, StudentGroup.class);

            createBackup(students, db);
        }
    }

    public void printStudentByID(int id, StudentDAO db) {
        if (db.getStudentById(id).isPresent()) {
            db.getStudentById(id).get().printInfo();
        } else {
            printEntireDB(db);
        }
    }

    private void printEntireDB(StudentDAO db) {
        if (!db.isEmpty()) {
            printAllElements(db.getStudents());
        }
    }

    protected static boolean fileExists(String string) {
        File file = new File(string);
        if (file.exists() && !(file.isDirectory())) {
            return true;
        } else {
            return false;
        }
    }

    protected static void printStudents(StudentGroup studentGroup) {
        printAllElements(studentGroup);
    }

    protected static void createBackup(StudentGroup students, StudentDAO db) {
        db.insertStudents(students);
    }

    protected static void printAllElements(StudentGroup students) {
        if (students.getStudents() != null) {
            students.getStudents().forEach(student -> student.printInfo());
        }
    }
    
    public void writeInBothDBs(String path) throws FileNotFoundException {
        writeInDB(path, mongo);
        writeInDB(path, mysql);
    }
    
    public void printStudentByIDFromBothDBs(int id) {
        printStudentByID(id, mongo);
        printStudentByID(id, mysql);
    }

}
