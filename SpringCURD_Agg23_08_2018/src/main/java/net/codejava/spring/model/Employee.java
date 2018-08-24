package net.codejava.spring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Employee")
public class Employee {
	// @Id
	// private int id;
	private String name;
	private String email;
	private String address;
	private String telephone;
	private int salary;
	private String deportment;

	public Employee() {
	}

	public Employee(String name, String email, String address, String telephone, int salary, String deportment) {
		this.name = name;
		this.email = email;
		this.address = address;
		this.telephone = telephone;
		this.salary = salary;
		this.deportment = deportment;
	}

	// public int getId() {
	// return id;
	// }
	//
	// public void setId(int id) {
	// this.id = id;
	// }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getDeportment() {
		return deportment;
	}

	public void setDeportment(String deportment) {
		this.deportment = deportment;
	}

}
