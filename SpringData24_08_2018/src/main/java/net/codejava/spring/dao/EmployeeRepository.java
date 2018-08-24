package net.codejava.spring.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import net.codejava.spring.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, String>, EmployeeRepositoryExt {

}
