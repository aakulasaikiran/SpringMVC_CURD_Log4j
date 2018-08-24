package net.codejava.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.spring.dao.EmployeeRepository;
import net.codejava.spring.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private EmployeeRepository employeeRepository;

	@Autowired
	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public List<Employee> list() {
		List<Employee> listEmployee = (List<Employee>) employeeRepository.findAll();
		return listEmployee;
	}

	public void saveOrUpdate(Employee employee) {
		employeeRepository.save(employee);
	}

	public void delete(String email) {
		// employeeRepository.delete(email);
		employeeRepository.delete(email);
	}

	public List<Employee> sortbysalary() {
		List<Employee> employeelist = employeeRepository.sortbysalary();
		return employeelist;
	}

	public Employee getedit(String email, Employee employee) {
		Employee employee1 = employeeRepository.getedit(email, employee);
		return employee1;

	}

	public void EUpdate(Employee employee1) {
		employeeRepository.EUpdate(employee1);

	}

	public Set<String> getDepartments() {
		Set<String> st = employeeRepository.getDepartments();
		return st;

	}

	public Employee aggregate(String employeeDepartment) {
		Employee contact = employeeRepository.aggregate(employeeDepartment);
		return contact;
	}

	public List<Employee> groupByDepList(String groupname) {

		List<Employee> employeelist = employeeRepository.groupByDepList1(groupname);

		return employeelist;
	}

	public void deleteAll() {

		employeeRepository.deleteAll();
	}

	public int totalcount() {
		int count=(int) employeeRepository.count();
		return count;
	}
}