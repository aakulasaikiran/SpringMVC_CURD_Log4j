package net.codejava.spring.service;

import java.util.List;
import java.util.Set;

import net.codejava.spring.model.Employee;

public interface EmployeeService {

	public List<Employee> list();

	public void saveOrUpdate(Employee employee);

	public void delete(String email);

	public List<Employee> sortbysalary();

	public Employee getedit(String email, Employee employee);

	public void EUpdate(Employee employee1);

	public Set<String> getDepartments();

	Employee aggregate(String employeeDepartment);

	public List<Employee> groupByDepList(String groupname);
	
	public void deleteAll();
	public int totalcount();
}
