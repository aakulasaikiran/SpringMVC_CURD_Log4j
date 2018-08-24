package net.codejava.spring.dao;

import org.springframework.data.repository.CrudRepository;

import net.codejava.spring.model.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long>, EmployeeDAO {

}
