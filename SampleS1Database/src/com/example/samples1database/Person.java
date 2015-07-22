package com.example.samples1database;

public class Person {
	long id;
	String name;
	int age;
	
	@Override
	public String toString() {
		return name + "(" + age + ")";
	}
}
