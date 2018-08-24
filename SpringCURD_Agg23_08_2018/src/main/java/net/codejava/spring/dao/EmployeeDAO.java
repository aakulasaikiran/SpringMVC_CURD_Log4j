package net.codejava.spring.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.mapreduce.GroupByResults;


import net.codejava.spring.model.Employee;

/**
 * Defines DAO operations for the employee model.
 * 
 * @author saikiran
 *
 */
public interface EmployeeDAO {

	public void saveOrUpdate(Employee employee);

	public void delete(String email);

	public Employee get(String email, Employee employee);

	public Employee getedit(String email, Employee employee);

	public List<Employee> list();

	public void Update(Employee employee1);

	public List<Employee> groupByDep(String groupname);

	public List<Employee> sortbysalary();
	public Set<String> getDepartments();
	Employee aggregate(String employeeDepartment);
}
