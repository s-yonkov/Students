package com.musala.simple.students.db;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StudentGroup {

	@SerializedName("students")
	@Expose
	private List<Student> students = null;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public Student getStudentById(int id) {

		if (id <= this.students.size()) {
			return this.students.get(id - 1);
		} else {
			throw new IllegalArgumentException("Requested student does not exist in the data.");
		}
	}

}
