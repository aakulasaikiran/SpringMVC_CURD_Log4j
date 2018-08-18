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
import net.codejava.spring.service.EmployeeService;

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
	@Autowired
	private EmployeeService employeeService;

	/*
	 * This is for First Request access like "/" and rendering home.jsp with
	 * InternalResourceViewResolver
	 */
	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {

		System.out.println(logger.getName());
		logger.info("'sup? I'm your info logger");

		List<Employee> listEmployee = employeeService.list();
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
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		logger.info("saveEmployee method()");
		employeeService.saveOrUpdate(employee);
		return new ModelAndView("redirect:/");
	}

	/*
	 * This is for Request access like "/deleteEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		logger.info("deleteEmployee method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
		// employeeDAO.delete(email);

		employeeService.delete(email);

		return new ModelAndView("redirect:/");
	}

	/*
	 * This is for Request access like "/editEmployee" and rendering to
	 * redirect:/ or EmployeeForm.jsp with InternalResourceViewResolver
	 */

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, @ModelAttribute Employee employee) {
		logger.info("editEmployee method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
		// Employee employee1 = (Employee) employeeDAO.getedit(email, employee);
		Employee employee1 = (Employee) employeeService.getedit(email, employee);
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
	public ModelAndView updateEmployee(@ModelAttribute Employee employee1) {
		logger.info("updateEmployee method()");

		employeeService.Update(employee1);

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/groupByDep", method = RequestMethod.GET)
	public ModelAndView groupByDep(ModelAndView model) {
		logger.info("groupByDep method()");
		/*
		 * Employee newEmployee = new Employee(); logger.debug(
		 * "groupByDep value " + newEmployee); model.addObject("employee",
		 * newEmployee); model.setViewName("EmployeeForm"); logger.debug(
		 * "model value " + model);
		 */
		model.setViewName("GroupPage");
		return model;

	}

	@RequestMapping(value = "/groupbysoftware", method = RequestMethod.GET)
	public ModelAndView groupbysoftware(HttpServletRequest request, ModelAndView model) {
		logger.info("groupbysoftware method()");
		String groupname = "Software";
		logger.debug("GroupName" + groupname);

		List<Employee> listEmployee = employeeService.groupByDepsoftware(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

	@RequestMapping(value = "/groupbytester", method = RequestMethod.GET)
	public ModelAndView groupbytester(HttpServletRequest request, ModelAndView model) {
		logger.info("groupbytester method()");
		String groupname = "Tester";
		logger.debug("GroupName" + groupname);

		List<Employee> listEmployee = employeeService.groupByDeptester(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

	@RequestMapping(value = "/groupbymanager", method = RequestMethod.GET)
	public ModelAndView groupbymanager(HttpServletRequest request, ModelAndView model) {
		logger.info("groupbymanager method()");
		String groupname = "Manager";
		logger.debug("GroupName" + groupname);

		List<Employee> listEmployee = employeeService.groupByDepmanager(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

	@RequestMapping(value = "/groupbyadmin", method = RequestMethod.GET)
	public ModelAndView groupbyadmin(HttpServletRequest request, ModelAndView model) {
		logger.info("groupbyadmin method()");
		String groupname = "Admin";
		logger.debug("GroupName" + groupname);

		List<Employee> listEmployee = employeeService.groupByDepadmin(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

}
