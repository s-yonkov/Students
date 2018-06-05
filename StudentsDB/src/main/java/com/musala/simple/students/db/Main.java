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

public class Main {

	public static void main(String[] args) throws StudentDataParsingException {

		final Logger logger = LoggerFactory.getLogger(Main.class);
		Gson gson = new Gson();
		// Will modofy once we have more than one DB - it may change to new MySqlDB
		DataBase db = new MongoDB();

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
			} else if (args.length == 2 && isValidId(args[1])) {
				// Checking if there are 2 arguments passed
				int id = Integer.parseInt(args[1]);
				if (id <= students.getStudents().size()) {
					students.getStudentById(id - 1).printInfo();
				} else {
					logger.info("Requested student does not exist in the data");
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

	private static boolean fileExists(String string) {
		File file = new File(string);
		if (file.exists() && !(file.isDirectory())) {
			return true;
		} else {
			return false;
		}
	}

	private static void printFromBkp(StudentGroup studentGroup) {
		printAllElements(studentGroup);
	}

	private static void createBackup(StudentGroup students, DataBase db) {
		db.insertStudents(students);
	}

	public static void printAllElements(StudentGroup students) {
		if (students != null) {
			students.getStudents().forEach(student -> student.printInfo());
		}
	}

	public static boolean isValidId(String id) {
		try {
			Integer.parseInt(id);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
