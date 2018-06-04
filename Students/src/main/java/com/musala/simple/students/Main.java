package com.musala.simple.students;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class Main {

	public static void main(String[] args) {

		Gson gson = new Gson();
		BufferedReader br = null;
		ClassLoader classLoader = new Main().getClass().getClassLoader();
		File bkpFile = new File(classLoader.getResource("backup.json").getFile());

		if (args.length != 0) {
			// If the passed path is incorrect and there is available bkp file
			if (!(fileExists(args[0])) && (bkpFile.exists() && !(bkpFile.isDirectory()))) {
				printFromBkp(bkpFile);
			}
		}

		try {
			br = new BufferedReader(new FileReader(new File(args[0])));
			// If the file exists parsing the JSON in StudentGroup
			StudentGroup students = gson.fromJson(br, StudentGroup.class);
			// Creating bkp file with the available information
			createBackup(students, bkpFile);

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

	private static void printFromBkp(File bkpFile) {
		Gson gson = new Gson();
		try {
			BufferedReader br = new BufferedReader(new FileReader(bkpFile));
			StudentGroup studentsBkp = gson.fromJson(br, StudentGroup.class);
			printAllElements(studentsBkp);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	private static void createBackup(StudentGroup students, File bkpFile) {
		try {
			Gson gson = new Gson();
			String jsonString = gson.toJson(students);
			FileWriter fileWriter = new FileWriter(bkpFile);
			fileWriter.write(jsonString);
			fileWriter.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
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