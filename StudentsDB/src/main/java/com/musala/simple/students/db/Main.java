package com.musala.simple.students.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.musala.simple.students.db.exceptions.SQLConnectionException;
import com.musala.simple.students.db.exceptions.StudentDataParsingException;
import com.musala.simple.students.db.impl.MongoDB;
import com.musala.simple.students.db.impl.MySqlDB;

public class Main {

    final static Logger LOGGER = LoggerFactory.getLogger(Main.class);
    private static Gson gson = new Gson();
    protected static DataBase db;

    public static void main(String[] args) throws StudentDataParsingException, SQLConnectionException {

        // Using the third argument as a key how many and which DB to use - 3 options
        // one for Mongo, one for MySQL and one for both.
        if (args.length == 3 && args != null && args[2] != null) {
            String dbChoice = args[2].toLowerCase();
            switch (dbChoice) {
                case "mongo":
                    workWithOneDb((db = new MongoDB()), args);
                    break;
                case "mysql":
                    workWithOneDb((db = new MySqlDB()), args);
                    break;
                case "both":
                    workWithTwoDBs((db = new MySqlDB()), (db = new MongoDB()), args);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid argument passed");
            }
        } else if (args.length < 3 && args.length > 0) {
            workWithOneDb(db = new MySqlDB(), args);
        } else if (args.length == 3 && args[2] == null) {
            throw new IllegalArgumentException("The third argument should not be null");
        } else {
            throw new IllegalArgumentException("Invalid number of arguments passed");
        }

    }

    protected static void workWithTwoDBs(DataBase mySQL, DataBase mongo, String[] args)
            throws StudentDataParsingException, SQLConnectionException {
        workWithOneDb(mySQL, args);
        workWithOneDb(mongo, args);
    }

    protected static void workWithOneDb(DataBase db, String[] args)
            throws StudentDataParsingException, SQLConnectionException {

        if (args.length != 0 && args != null) {
            // If the passed path is incorrect and the db is not empty
            if (!(fileExists(args[0])) && (!db.isEmpty())) {
                printFromBkp(db.getStudents());
            }
        }

        try (BufferedReader br = new BufferedReader(new FileReader(new File(args[0])));) {
            // If the file exists parsing the JSON in StudentGroup
            StudentGroup students = gson.fromJson(br, StudentGroup.class);
            // Creating bkp file with the available information
            createBackup(students, db);

            if (args.length == 1) {
                // If only one argument is passed printing the entire info
                printAllElements(students);
            } else if (args.length >= 2 && isValidId(args[1])) {

                int id = Integer.parseInt(args[1]);
                if (id <= students.getStudents().size()) {
                    students.getStudentById(id).printInfo();
                } else {
                    LOGGER.warn("Requested student does not exist in the data");
                    printAllElements(students);
                }
            }
        } catch (FileNotFoundException fileNotFound) {
            throw new StudentDataParsingException("The file is not found", fileNotFound);
        } catch (JsonSyntaxException jsonSyntaxException) {
            throw new StudentDataParsingException("The file is not valid or malforemd", jsonSyntaxException);
        } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
            throw new StudentDataParsingException("No arguments are passed.", arrayIndexOutOfBoundsException);
        } catch (IOException ioException) {
            throw new StudentDataParsingException("Could not retrieve info from the file", ioException);
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

    protected static void printFromBkp(StudentGroup studentGroup) throws StudentDataParsingException {
        printAllElements(studentGroup);
    }

    protected static void createBackup(StudentGroup students, DataBase db) throws SQLConnectionException {
        db.insertStudents(students);
    }

    protected static void printAllElements(StudentGroup students) {
        if (students.getStudents() != null) {
            students.getStudents().forEach(student -> student.printInfo());
        }
    }

    protected static boolean isValidId(String id) {
        try {
            Integer.parseInt(id);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
