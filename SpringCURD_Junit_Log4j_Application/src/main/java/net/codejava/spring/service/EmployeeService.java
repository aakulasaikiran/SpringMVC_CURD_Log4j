package net.codejava.spring.service;

import java.util.List;

import net.codejava.spring.model.Employee;

public interface EmployeeService {
	public void delete(String email);

	public List<Employee> groupByDepsoftware(String groupname);

	public List<Employee> groupByDeptester(String groupname);

	public List<Employee> groupByDepmanager(String groupname);

	public List<Employee> groupByDepadmin(String groupname);

	public List<Employee> list();

	public void saveOrUpdate(Employee employee);

	public Employee getedit(String email, Employee employee);

	public void Update(Employee employee1);
	
	public List<Employee> sortbysalary();
}
