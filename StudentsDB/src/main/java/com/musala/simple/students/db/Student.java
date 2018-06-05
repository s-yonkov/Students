package com.musala.simple.students.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	final Logger slf4jLogger = LoggerFactory.getLogger(Student.class);

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
		slf4jLogger.info("Id: {}, Name: {}, age: {}, grade: {} \n", this.id, this.name, this.age, this.grade);
	}
}
