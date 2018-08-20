package net.codejava.spring.servicetest;

import net.codejava.spring.config.MvcConfiguration;
import net.codejava.spring.controller.HomeController;
import net.codejava.spring.dao.EmployeeDAO;
import net.codejava.spring.model.Employee;
import net.codejava.spring.service.EmployeeService;
import net.codejava.spring.service.EmployeeServiceImpl;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Validator;

import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MvcConfiguration.class })
public class EmployeeServiceTest {
	ApplicationContext ctx = new AnnotationConfigApplicationContext(
			MvcConfiguration.class);
	
	private MockMvc mockMvc;
	private Mockery context;
	@Mock
	@InjectMocks
	//private EmployeeService employeeService;
 EmployeeService mockObject;


	 private List<Employee> listemployeelist;
	/*@InjectMocks
	private HomeController employeeController;*/

	
	@Mock
	private EmployeeDAO employeeDAOImpl;
	@Before
	public void init() {
		 context = new JUnit4Mockery();
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(employeeDAOImpl)

				.build();
	}


	@Before
	 public void setUp() {
	 
	  context = new JUnit4Mockery();
	  mockObject = context.mock(EmployeeServiceImpl.class);
	 
	  listemployeelist = new ArrayList<Employee>();
	 
	 
	 }

	
	 @Test
    public void list() throws Exception {
        List<Employee> users = Arrays.asList(
                new Employee("saikiran","sai@gmail.com","chelgal","8899889977",10000,"Software"));

        when(employeeDAOImpl.list()).thenReturn(users);

        
        verify(mockObject, times(1)).list();
        verifyNoMoreInteractions(mockObject);
    }
	
	
	 @Test
	 public void savemethod(){
	 final Employee employee=
		 new Employee("saikiran","sai@gmail.com","chelgal","8899889977",10000,"Software");
  
	       
	    
	     context.checking(new Expectations(){
	   {
	    oneOf(mockObject).saveOrUpdate(employee);
	    will(returnValue(employee));
	   }
	  });
	     mockObject.saveOrUpdate(employee);
	     context.assertIsSatisfied();
	     
	 
	  
	 }
	
	
	
	
	
	
	
	
	
}
