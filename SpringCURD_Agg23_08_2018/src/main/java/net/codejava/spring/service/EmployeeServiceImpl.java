package net.codejava.spring.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.codejava.spring.dao.EmployeeDAO;
import net.codejava.spring.dao.EmployeeRepository;
import net.codejava.spring.model.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	@Autowired
	private EmployeeDAO employeeDAO;

	public void delete(String email) {
		employeeDAO.delete(email);

	}

	public List<Employee> list() {
		List<Employee> listEmployee = employeeDAO.list();
		return listEmployee;
	}

	public void saveOrUpdate(Employee employee) {
		employeeDAO.saveOrUpdate(employee);

	}

	public Employee getedit(String email, Employee employee) {
		Employee employee1 = (Employee) employeeDAO.getedit(email, employee);
		return employee1;
	}

	public void Update(Employee employee1) {
		employeeDAO.Update(employee1);

	}

	public List<Employee> groupByDepsoftware(String groupname) {
		String deportment = "Software";
		List<Employee> listEmployee;
		if (deportment.equalsIgnoreCase(groupname)) {
			listEmployee = employeeDAO.groupByDep(groupname);
		} else {
			return null;
		}

		return listEmployee;
	}

	public List<Employee> groupByDeptester(String groupname) {
		String deportment = "Tester";
		List<Employee> listEmployee;
		if (deportment.equalsIgnoreCase(groupname)) {
			listEmployee = employeeDAO.groupByDep(groupname);
		} else {
			return null;
		}

		return listEmployee;
	}

	public List<Employee> groupByDepmanager(String groupname) {
		String deportment = "Manager";
		List<Employee> listEmployee;
		if (deportment.equalsIgnoreCase(groupname)) {
			listEmployee = employeeDAO.groupByDep(groupname);
		} else {
			return null;
		}

		return listEmployee;
	}

	public List<Employee> groupByDepadmin(String groupname) {
		String deportment = "Admin";
		List<Employee> listEmployee;
		if (deportment.equalsIgnoreCase(groupname)) {
			listEmployee = employeeDAO.groupByDep(groupname);
		} else {
			return null;
		}

		return listEmployee;

	}

	public List<Employee> sortbysalary() {
		List<Employee> employeelist = employeeDAO.sortbysalary();
		return employeelist;
	}

	public Set<String> getDepartments() {
		Set<String> st=employeeDAO.getDepartments();
		return st;
	}

	public Employee aggregate(String employeeDepartment) {
		Employee contact=employeeDAO.aggregate(employeeDepartment);
		return contact;
	}
}