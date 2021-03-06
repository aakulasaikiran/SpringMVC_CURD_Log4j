package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.dao.EmployeeDAO;
import net.codejava.spring.model.Employee;

/**
 * This controller routes accesses to the application to the appropriate hanlder
 * methods.
 * 
 * @author saikiran
 *
 */
@Controller
public class HomeController {
	Logger logger = LoggerFactory.getLogger("HomeController");
	@Autowired
	private EmployeeDAO employeeDAO;

	/*
	 * This is for First Request access like "/" and rendering home.jsp with
	 * InternalResourceViewResolver
	 */
	@RequestMapping(value = "/")
	public ModelAndView listContact(ModelAndView model) throws IOException {

		System.out.println(logger.getName());
		logger.info("'sup? I'm your info logger");

		List<Employee> listEmployee = employeeDAO.list();
		logger.debug("Employee List at First Request:" + listEmployee);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		// logger.info(model);
		return model;
	}

	/*
	 * This is for Request access like "/newEmployee" and rendering
	 * EmployeeForm.jsp with InternalResourceViewResolver
	 */
	@RequestMapping(value = "/newEmployee", method = RequestMethod.GET)
	public ModelAndView newEmployee(ModelAndView model) {
		logger.info("newEmployee method()");
		Employee newEmployee = new Employee();
		logger.debug("newEmployee value " + newEmployee);
		model.addObject("employee", newEmployee);
		model.setViewName("EmployeeForm");
		logger.debug("model value " + model);
		return model;

	}

	/*
	 * This is for Request access like "/saveEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveContact(@ModelAttribute Employee employee) {
		logger.info("saveContact method()");
		employeeDAO.saveOrUpdate(employee);
		return new ModelAndView("redirect:/");
	}

	/*
	 * This is for Request access like "/deleteEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteContact(HttpServletRequest request) {
		logger.info("deleteContact method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
		employeeDAO.delete(email);
		return new ModelAndView("redirect:/");
	}

	/*
	 * This is for Request access like "/editEmployee" and rendering to
	 * redirect:/ or EmployeeForm.jsp with InternalResourceViewResolver
	 */
	
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editContact(HttpServletRequest request, @ModelAttribute Employee employee) {
		logger.info("editContact method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
		Employee employee1 = (Employee) employeeDAO.getedit(email, employee);

		ModelAndView model = new ModelAndView("UpdateForm");

		model.addObject("employee1", employee1);
		logger.debug("Model value" + model);
		// employeeDAO.saveOrUpdate(employee);
		return model;
	}

	/*
	 * This is for Request access like "/saveEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateContact(@ModelAttribute Employee employee1) {
		logger.info("updateContact method()");
		employeeDAO.Update(employee1);
		return new ModelAndView("redirect:/");
	}

}
