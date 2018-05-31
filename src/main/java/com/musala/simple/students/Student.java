package com.musala.simple.students;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Student {

	@SerializedName("id")
	@Expose
	private Integer id;
	@SerializedName("name")
	@Expose
	private String name;
	@SerializedName("age")
	@Expose
	private Integer age;
	@SerializedName("grade")
	@Expose
	private Integer grade;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}

	public void printInfo() {
		System.out.printf("Id: %d, Name: %s, age: %d, grade: %d \n", this.id, this.name, this.age, this.grade);
	}
}
