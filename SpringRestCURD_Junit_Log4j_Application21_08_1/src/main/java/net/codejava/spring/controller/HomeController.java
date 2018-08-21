package net.codejava.spring.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	/*
	 * description about method This is for First Request access like "/" and
	 * rendering home.jsp with InternalResourceViewResolver
	 */
	// method1)
	// For SpringMvc type and it return model and view so we can't store
	// employee details into file if you need to store result into file you need
	// method2

	@RequestMapping(value = "/")
	public ModelAndView listEmployee(ModelAndView model) throws IOException {

		System.out.println(logger.getName());
		logger.info("'sup? I'm your info logger");
		List<Employee> listEmployee = employeeService.list();
		logger.debug("Employee List at First Request:" + listEmployee);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("home");
		logger.info("model values :" + model);
		return model;
	}

	// Method2)output as List<Employee> in JSON Form
	/*
	 * @RequestMapping(value = "/",produces = "application/json") optional
	 * things
	 * 
	 * @Path("/")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET public List<Employee> listEmployee(ModelAndView model) throws
	 * IOException { System.out.println(logger.getName()); logger.info(
	 * "'sup? I'm your info logger"); List<Employee> listEmployee =
	 * employeeService.list(); logger.debug("Employee List at First Request:" +
	 * listEmployee); model.addObject("listEmployee", listEmployee);
	 * model.setViewName("home"); // logger.info(model); return listEmployee; }
	 */

	// For rest full webservice for json form out put
	/*
	 * method3) for Json Form out put
	 * 
	 * @RequestMapping(value = "/",produces = "application/json")
	 * 
	 * @Path("/")
	 * 
	 * @Produces(MediaType.APPLICATION_JSON)
	 * 
	 * @GET public Employee listEmployee(ModelAndView model) throws IOException
	 * { Employee employee=new
	 * Employee("saikiran","sai@gmail.com","chelgal","8899889977",10000,
	 * "Software"); return employee; }
	 */
	/*
	 * This is for Request access like "/newEmployee" and rendering
	 * EmployeeForm.jsp with InternalResourceViewResolver
	 */
	// it is for Spring MVC type
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

	// method2) for Json type output but it will fill with default values like
	// null
	/*
	 * @RequestMapping(value = "/newEmployee", method =
	 * RequestMethod.GET,produces = "application/json") public Employee
	 * newEmployee(ModelAndView model) { logger.info("newEmployee method()");
	 * Employee newEmployee = new Employee(); logger.debug("newEmployee value "
	 * + newEmployee); model.addObject("employee", newEmployee);
	 * model.setViewName("EmployeeForm"); logger.debug("model value " + model);
	 * return newEmployee;
	 * 
	 * }
	 */

	/*
	 * for springMVC type This is for Request access like "/saveEmployee" and
	 * rendering to redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	// method1) for springmvc
	@RequestMapping(value = "/saveEmployee", method = RequestMethod.POST/*, produces = "application/json", consumes = "application/json"*/)
	public ModelAndView saveEmployee(/*@RequestBody Employee employee1*/@ModelAttribute Employee employee1) {
		logger.info("saveEmployee method()");
		employeeService.saveOrUpdate(employee1);

		return new ModelAndView("redirect:/");
	}

	// method2)forJson type out put it will return all employee with include the
	// new employee dtails as json
	/*
	 * @RequestMapping(value = "/saveEmployee", method =
	 * RequestMethod.POST,produces = "application/json") public List<Employee>
	 * saveEmployee(@ModelAttribute Employee employee) { logger.info(
	 * "saveEmployee method()"); employeeService.saveOrUpdate(employee);
	 * List<Employee> listEmployee = employeeService.list(); return
	 * listEmployee; }
	 */

	/*
	 * This is for Request access like "/deleteEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	// method1) for spring mvc type with out path param ondly @RequestParam
	/*@RequestMapping(value = "/deleteEmployee", method = RequestMethod.GET)
	public ModelAndView deleteEmployee(HttpServletRequest request, @RequestParam String email) {
		logger.info("deleteEmployee method()");
		logger.debug("Email value" + email);
		employeeService.delete(email);
		return new ModelAndView("redirect:/");
	}*/
	// method2) for spring mvc type with path param @PathVariable (problem)
			
		/*@RequestMapping(value = "/deleteEmployee/{email}", method = RequestMethod.GET)
			public ModelAndView deleteEmployee(HttpServletRequest request, @RequestParam String email
					@PathVariable String email) {
				logger.info("deleteEmployee method()");
				logger.debug("Email value" + email);
				employeeService.delete(email);
				return new ModelAndView("redirect:/");
			}
	*/
	
	// method3) for spring mvc type with path param @PathVariable
	//note: case1)if you use email with request(@PathVariable) must and should specify Formate like /deleteEmployee/{email:.+} then ondly it will take after .characters or words 
		
	@RequestMapping(value = "/deleteEmployee/{email:.+}", method = RequestMethod.GET)
		public ModelAndView deleteEmployee(HttpServletRequest request, @RequestParam String email1,
				@PathVariable String email) {
			logger.info("deleteEmployee method()");
			logger.debug("Email value" + email);
			employeeService.delete(email);
			return new ModelAndView("redirect:/");
		}

	// method2) json type it store alldetails expect present record if you give
	// emailid from Query param way it will delete properly
	// like :
	// http://localhost:8081/SpringRestCURD_Junit_Log4j_Application/deleteEmployee?email=sai440@gmail.com
	// it will delete sai440@gmail.com details and return remaining details

	/*
	 * @RequestMapping(value = "/deleteEmployee", method =
	 * RequestMethod.GET,produces = "application/json") public List<Employee>
	 * deleteEmployee(@QueryParam("email") String email,HttpServletRequest
	 * request) { logger.info("deleteEmployee method()"); //String email =
	 * request.getParameter("email"); logger.debug("Email value" + email);
	 * 
	 * employeeService.delete(email); List<Employee> listEmployeed =
	 * employeeService.list(); return listEmployeed; }
	 */

	/*
	 * This is for Request access like "/editEmployee" and rendering to
	 * redirect:/ or EmployeeForm.jsp with InternalResourceViewResolver
	 */
	// method 1) for SpringMVC type
	@RequestMapping(value = "/editEmployee", method = RequestMethod.GET)
	public ModelAndView editEmployee(HttpServletRequest request, @ModelAttribute Employee employee/*,@RequestParam String email*/) {
		logger.info("editEmployee method()");
		String email = request.getParameter("email");
		logger.debug("Email value" + email);
        Employee employee1 = (Employee) employeeService.getedit(email, employee);
		ModelAndView model = new ModelAndView("UpdateForm");
        model.addObject("employee1", employee1);
		logger.debug("Model value" + model);
        return model;
	}

	// method 2) for it will return Employee with
	// give request like :
	// http://localhost:8081/SpringRestCURD_Junit_Log4j_Application/editEmployee?email=vinod@gmail.com
	// then it will display all details which email id you given
	/*
	 * @RequestMapping(value = "/editEmployee", method = RequestMethod.GET,
	 * produces = "application/json") public Employee
	 * editEmployee(@QueryParam("email") String email,HttpServletRequest
	 * request, @ModelAttribute Employee employee) { logger.info(
	 * "editEmployee method()"); //String email = request.getParameter("email");
	 * logger.debug("Email value" + email);
	 * 
	 * Employee employee1 = (Employee) employeeService.getedit(email, employee);
	 * ModelAndView model = new ModelAndView("UpdateForm");
	 * 
	 * model.addObject("employee1", employee1); logger.debug("Model value" +
	 * model);
	 * 
	 * return employee1; }
	 */

	/*
	 * This is for Request access like "/saveEmployee" and rendering to
	 * redirect:/ or home.jsp with InternalResourceViewResolver
	 */
	// mthod 1) For SpringMVC type

	@RequestMapping(value = "/updateEmployee", method = RequestMethod.POST/*, produces = "application/json", consumes = "application/json"*/)
	public ModelAndView updateEmployee(@ModelAttribute Employee employee1 ,@RequestBody(required=false) Employee employee2) {
		logger.info("updateEmployee method()");
        employeeService.Update(employee1);
        return new ModelAndView("redirect:/");
	}

	// method2) for json object form

	/*
	 * @RequestMapping(value = "/updateEmployee", method =
	 * RequestMethod.POST,produces = "application/json") public List<Employee>
	 * updateEmployee(@RequestBody Employee employee2,@ModelAttribute Employee
	 * employee1) { logger.info("updateEmployee method()");
	 * 
	 * employeeService.Update(employee1); List<Employee> listEmployee2 =
	 * employeeService.list(); return listEmployee2; }
	 */

	// method 1) for springmvc
	/*
	 * @RequestMapping(value = "/groupByDep", method = RequestMethod.GET) public
	 * ModelAndView groupByDep(ModelAndView model) { logger.info(
	 * "groupByDep method()");
	 * 
	 * Employee newEmployee = new Employee(); logger.debug( "groupByDep value "
	 * + newEmployee); model.addObject("employee", newEmployee);
	 * model.setViewName("EmployeeForm"); logger.debug( "model value " + model);
	 * 
	 * model.setViewName("GroupPage"); return model;
	 * 
	 * }
	 */

	@RequestMapping(value = "/groupByDep", method = RequestMethod.GET)
	public ModelAndView groupByDep(ModelAndView model) {
		logger.info("groupByDep method()");

		model.setViewName("GroupPage");
		return model;

	}

	// method1 for springmvc request
	@RequestMapping(value = "/groupbysoftware", method = RequestMethod.GET/*,produces = "application/json", consumes = "application/json"*/)
	public ModelAndView groupbysoftware(HttpServletRequest request, ModelAndView model
		,@RequestParam(required=false) String groupname2) {
		logger.info("groupbysoftware method()");
		String groupname1 = "Software";
		//logger.debug("GroupName" + groupname);
        List<Employee> listEmployee = employeeService.groupByDepsoftware(groupname1);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;
}

	// method 2) for json object itwill return list of employee group by
	// Softwares
	// youcan give request like :
	// http://localhost:8081/SpringRestCURD_Junit_Log4j_Application/groupbysoftware?groupname=Software
	/*
	 * @RequestMapping(value = "/groupbysoftware", method =
	 * RequestMethod.GET,produces = "application/json") public List<Employee>
	 * groupbysoftware(HttpServletRequest request, ModelAndView
	 * model,@QueryParam("groupname") String groupname) { logger.info(
	 * "groupbysoftware method()"); //String groupname = "Software";
	 * logger.debug("GroupName" + groupname);
	 * 
	 * List<Employee> listEmployee =
	 * employeeService.groupByDepsoftware(groupname);
	 * model.addObject("listEmployee", listEmployee);
	 * model.setViewName("GroupbyDeportment"); return listEmployee;
	 * 
	 * }
	 */

	// method 1) SpringMVC type

	@RequestMapping(value = "/groupbytester", method = RequestMethod.GET, produces = "application/json")
	public ModelAndView groupbytester(HttpServletRequest request, ModelAndView model ,@RequestParam(required=false)  String groupname2) {
		logger.info("groupbytester method()");
		String groupname1 = "Tester";
		logger.debug("GroupName" + groupname1);

		List<Employee> listEmployee = employeeService.groupByDeptester(groupname1);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

	// method 2) it is for json output form it will return list of employee of
	// testers

	/*
	 * @RequestMapping(value = "/groupbytester", method = RequestMethod.GET)
	 * public List<Employee> groupbytester(HttpServletRequest request,
	 * ModelAndView model,@QueryParam("groupname1") String groupname1) {
	 * logger.info("groupbytester method()"); //String groupname = "Tester";
	 * logger.debug("GroupName" + groupname1);
	 * 
	 * List<Employee> listEmployee =
	 * employeeService.groupByDeptester(groupname1);
	 * model.addObject("listEmployee", listEmployee);
	 * model.setViewName("GroupbyDeportment"); return listEmployee;
	 * 
	 * }
	 */
	// method1) for springmvc
	 @RequestMapping(value = "/groupbymanager", method = RequestMethod.GET,produces = "application/json")
	  public ModelAndView groupbymanager(HttpServletRequest request,ModelAndView model,@RequestParam(required=false) String groupname2) { 
		 logger.info("groupbymanager method()"); String
	 groupname = "Manager"; logger.debug("GroupName" + groupname);
	  List<Employee> listEmployee =employeeService.groupByDepmanager(groupname);
	  model.addObject("listEmployee", listEmployee);
	  model.setViewName("GroupbyDeportment"); return model;
	  
	  }
	 

	// method2)for json output
	/*
	 * @RequestMapping(value = "/groupbymanager", method =
	 * RequestMethod.GET,produces = "application/json") public List<Employee>
	 * groupbymanager(HttpServletRequest request, ModelAndView
	 * model,@QueryParam("groupname1") String groupname1) { logger.info(
	 * "groupbymanager method()"); String groupname = "Manager";
	 * logger.debug("GroupName" + groupname);
	 * 
	 * List<Employee> listEmployee =
	 * employeeService.groupByDepmanager(groupname);
	 * model.addObject("listEmployee", listEmployee);
	 * model.setViewName("GroupbyDeportment"); return listEmployee;
	 * 
	 * }
	 */

	// method 1)for springmvc
	@RequestMapping(value = "/groupbyadmin", method = RequestMethod.GET)
	public ModelAndView groupbyadmin(HttpServletRequest request, ModelAndView model,@RequestParam(required=false) String groupname2) {
		logger.info("groupbyadmin method()");
		String groupname = "Admin";
		logger.debug("GroupName" + groupname);

		List<Employee> listEmployee = employeeService.groupByDepadmin(groupname);
		model.addObject("listEmployee", listEmployee);
		model.setViewName("GroupbyDeportment");
		return model;

	}

	// method 2)for json
	/*
	 * @RequestMapping(value = "/groupbyadmin", method =
	 * RequestMethod.GET,produces = "application/json") public List<Employee>
	 * groupbyadmin(HttpServletRequest request, ModelAndView
	 * model,@QueryParam("groupname1") String groupname1) { logger.info(
	 * "groupbyadmin method()"); //String groupname = "Admin";
	 * logger.debug("GroupName" + groupname1);
	 * 
	 * List<Employee> listEmployee =
	 * employeeService.groupByDepadmin(groupname1);
	 * model.addObject("listEmployee", listEmployee);
	 * model.setViewName("GroupbyDeportment"); return listEmployee;
	 * 
	 * }
	 */
	// method1) fro Springmvc
	@RequestMapping(value = "/sortbySalary", method = RequestMethod.GET)
	public ModelAndView sortbysalary(HttpServletRequest request, ModelAndView model) {
		logger.info("sortbysalary method()");
		List<Employee> listEmployee = employeeService.sortbysalary();
		model.addObject("listEmployee", listEmployee);
		model.setViewName("SortbySalary");

		return model;

	}

	// method2) for json type
	/*
	 * @RequestMapping(value = "/sortbySalary", method = RequestMethod.GET)
	 * public List<Employee> sortbysalary(HttpServletRequest request,
	 * ModelAndView model) { logger.info("sortbysalary method()");
	 * List<Employee> listEmployee = employeeService.sortbysalary();
	 * model.addObject("listEmployee", listEmployee);
	 * model.setViewName("SortbySalary");
	 * 
	 * return listEmployee;
	 * 
	 * }
	 */

}
