package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import net.codejava.spring.model.Employee;
import net.codejava.spring.service.EmployeeService;

/**
 * This controller routes accesses to the application to the appropriate hanlder
 * methods.
 * 
 * @author saikiran
 *
 */
@RestController
public class HomeController {
	Logger logger = LoggerFactory.getLogger("HomeController");

	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {
		logger.info("'sup? I'm your info logger");
		List<Employee> listEmployee = employeeService.list();
		logger.debug("Employee List at First Request:" + listEmployee);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		logger.info("model values :" + model);
		return model;
	}

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

	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
	public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
		logger.info("saveEmployee method()");
		employeeService.saveOrUpdate(employee);

		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request) {
		String email = request.getParameter("email");
		logger.info("deleteEmployee method()");
		logger.debug("Email value" + email);
		employeeService.delete(email);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/sortbySalary", method = RequestMethod.GET)
	public ModelAndView sortbysalary(HttpServletRequest request, ModelAndView model) {
		logger.info("sortbysalary method()");
		List<Employee> listEmployee = employeeService.sortbysalary();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("SortbySalary");

		return model;

	}

	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, @ModelAttribute Employee employee) {
		logger.info("editEmployee method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
		Employee employee1 = (Employee) employeeService.getedit(email, employee);
		ModelAndView model = new ModelAndView("UpdateForm");
		model.addObject("employee1", employee1);
		logger.debug("Model value" + model);
		return model;
	}

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST)
	public ModelAndView updateEmployee(@ModelAttribute Employee employee1) {
		logger.info("updateEmployee method()");
		employeeService.EUpdate(employee1);
		return new ModelAndView("redirect:/");
	}

	@RequestMapping(value = "/aggregateEmployee", method = RequestMethod.GET)
	public ModelAndView aggregation(ModelAndView model) {
		Set<String> li = employeeService.getDepartments();
		model.addObject("listEmployee", li);
		model.setViewName("aggregation");
		return model;

	}

	@RequestMapping(value = "/aggregate", method = RequestMethod.GET)
	public ModelAndView aggregateContact(HttpServletRequest request) {
		String contactDepartment = request.getParameter("department");
		Employee employee = employeeService.aggregate(contactDepartment);
		ModelAndView model = new ModelAndView("aggregate");
		model.addObject("employee", employee);
		model.setViewName("aggregate");
		return model;

	}

	@RequestMapping(value = "/groupByDep", method = RequestMethod.GET)
	public ModelAndView groupByDep(ModelAndView model) {
		Set<String> li = employeeService.getDepartments();
		model.addObject("listEmployee", li);
		model.setViewName("GroupingPage");

		return model;

	}

	@RequestMapping(value = "/groupbyEmployeeList", method = RequestMethod.GET)
	public ModelAndView groupbysoftware(HttpServletRequest request, ModelAndView model) {
		logger.info("groupbysoftware method()");
		String groupname = request.getParameter("department");
		logger.debug("GroupName" + groupname);
		List<Employee> listEmployee = employeeService.groupByDepList(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	public ModelAndView deleteAll() {
		logger.info("deleteEmployee method()");
		employeeService.deleteAll();
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping(value = "/totalcount", method = RequestMethod.GET)
	public ModelAndView totalount(ModelAndView model) {
		logger.info("deleteEmployee method()");
		int count=employeeService.totalcount();
		model.setViewName("CountPage");
		model.addObject("count",count);
		//model.addObject(count);
		return model;
	}
}
