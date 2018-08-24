package net.codejava.spring.dao;

import java.util.List;
import java.util.Set;

import net.codejava.spring.model.Employee;

/**
 * Defines DAO operations for the employee model.
 * 
 * @author saikiran
 *
 */
public interface EmployeeRepositoryExt {

	
	public void delete(String email);
	public List<Employee> sortbysalary();
	public Employee getedit(String email, Employee employee);
	public void EUpdate(Employee employee1);
	public Set<String> getDepartments();
	Employee aggregate(String employeeDepartment);
	public List<Employee> groupByDepList1(String groupname);
}
