package com.musala.simple.students.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.MongoClient;

public class Main {

	public static void main(String[] args) {

		Gson gson = new Gson();
		BufferedReader br = null;
		MongoClient mongoClient = new MongoClient();
		DB database = mongoClient.getDB("studentsDB");
		DBCollection collection = database.getCollection("studentsCollection");

		if (args.length != 0) {
			// If the passed path is incorrect and the db is not empty
			if (!(fileExists(args[0])) && (collection.count() != 0)) {
				printFromBkp(collection);
			}
		}

		try {
			br = new BufferedReader(new FileReader(new File(args[0])));
			// If the file exists parsing the JSON in StudentGroup
			StudentGroup students = gson.fromJson(br, StudentGroup.class);
			// Creating bkp file with the available information
			createBackup(students, collection);

			if (args.length == 1) {
				// If only one argument is passed printing the entire info
				printAllElements(students);
			} else if (args.length == 2 && isValidId(args[1])) {
				// Checking if there are 2 arguments passed
				int id = Integer.parseInt(args[1]);
				if (id <= students.getStudents().size()) {
					students.getStudentById(id - 1).printInfo();
				} else {
					System.out.println("Requested student does not exist in the data");
					printAllElements(students);
				}
			}
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("The file is not found");
		} catch (JsonSyntaxException e) {
			throw new IllegalArgumentException("The file is not valid or malforemd");
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new IllegalArgumentException("No arguments are passed.");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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

	private static void printFromBkp(DBCollection collection) {
		DBCursor cursor = collection.find();
		while (cursor.hasNext()) {
			// should call a method for reverting from DB obj to Person obj - will check
			// Morphia
		}
	}

	private static void createBackup(StudentGroup students, DBCollection collection) {
		// should clear the DB before creating a new bkp
		students.getStudents().forEach(student -> collection.insert(StudentAdaptor.toDBObject(student)));
	}

	public static void printAllElements(StudentGroup students) {
		if (students != null) {
			for (Student student : students.getStudents()) {
				student.printInfo();
			}
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
