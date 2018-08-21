package net.codejava.spring.controllertest;


import com.fasterxml.jackson.databind.ObjectMapper;


import net.codejava.spring.config.MvcConfiguration;
import net.codejava.spring.controller.HomeController;
import net.codejava.spring.model.Employee;
import net.codejava.spring.service.EmployeeService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MvcConfiguration.class})
public class UserControllerUnitTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private HomeController employeeController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(employeeController)
                
                .build();
    }

    // =========================================== For first Request like "/" ==========================================
//Running Properlly
   /*@Test
    public void allemployees() throws Exception {
        List<Employee> users = Arrays.asList(
                new Employee("saikiran","sai@gmail.com","chelgal","8899889977",10000,"Software"));

        when(employeeService.list()).thenReturn(users);

        mockMvc.perform(get("/"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("home"));

        verify(employeeService, times(1)).list();
        verifyNoMoreInteractions(employeeService);
    }*/
    // =========================================== Delete Employee by Email=========================================
//delete case successfully
   /*@Test
    public void delete() throws Exception {
        Employee employee = new Employee("saikiran","sai@gmail.com","chelgal","8899889977",10000,"Software");
String email=employee.getEmail();
      employeeService.delete(email);

        mockMvc.perform(get("/newEmployee"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("EmployeeForm"));

        verify(employeeService, times(1)).delete(email);
        verifyNoMoreInteractions(employeeService);
    }*/
//---------------------------------------------saveOrUpdate-------------------
  /* @Test
    public void saveOrUpdate() throws Exception {
	   Employee employee = new Employee("saikiran","sai1@gmail.com","chelgal","8899889977",10000,"Software");

      employeeService.saveOrUpdate(employee);

        mockMvc.perform(post("/saveEmployee"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("home"));

        verify(employeeService, times(1)).saveOrUpdate(employee);
        verifyNoMoreInteractions(employeeService);
    }*/

    // =========================================== Create New Employee ========================================

   /* @Test
    public void createEmployee() throws Exception {
        Employee employee = new Employee("saikiran","sai1@gmail.com","chelgal","8899889977",10000,"Software");
        employeeService.saveOrUpdate(employee);
       

        employeeService.saveOrUpdate(employee);

        mockMvc.perform(post("/saveEmployee"))
        .andExpect(status().isOk())
        .andExpect(forwardedUrl("home"));

        verify(employeeService, times(1)).saveOrUpdate(employee);
        verifyNoMoreInteractions(employeeService);
    }*/
//---------------------------------------------------------------------------
   /* @Test
    public void test_create_user_fail_404_not_found() throws Exception {
        User user = new User("username exists");

        when(userService.exists(user)).thenReturn(true);

        mockMvc.perform(
                post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isConflict());

        verify(userService, times(1)).exists(user);
        verifyNoMoreInteractions(userService);
    }*/

    // =========================================== Update Existing Employee ===================================

    @Test
    public void editEmployee() throws Exception {
    	Employee employee = new Employee("saikiran4","sai1@gmail.com","chelgal","8899889977",10000,"Software");
        String email=employee.getEmail();
      when(employeeService.getedit(email, employee)).thenReturn(employee);
       

       mockMvc.perform(get("/editEmployee"))
       .andExpect(status().isOk())
       .andExpect(forwardedUrl("UpdateForm"));
        verify(employeeService, times(1)).getedit(email, employee);
        //verify(employeeService, times(1)).Update(employee);
        verifyNoMoreInteractions(employeeService);
    }

   /* @Test
    public void test_update_user_fail_404_not_found() throws Exception {
        User user = new User(999, "user not found");

        when(userService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                put("/users/{id}", user.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(user)))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }*/

    // =========================================== Delete User ============================================

    /*@Test
    public void test_delete_user_success() throws Exception {
        User user = new User(1, "Arya Stark");

        when(userService.findById(user.getId())).thenReturn(user);
        doNothing().when(userService).delete(user.getId());

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isOk());

        verify(userService, times(1)).findById(user.getId());
        verify(userService, times(1)).delete(user.getId());
        verifyNoMoreInteractions(userService);
    }*/

   /* @Test
    public void test_delete_user_fail_404_not_found() throws Exception {
        User user = new User(999, "user not found");

        when(userService.findById(user.getId())).thenReturn(null);

        mockMvc.perform(
                delete("/users/{id}", user.getId()))
                .andExpect(status().isNotFound());

        verify(userService, times(1)).findById(user.getId());
        verifyNoMoreInteractions(userService);
    }*/

    // =========================================== CORS Headers ===========================================

   /* @Test
    public void test_cors_headers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(header().string("Access-Control-Allow-Origin", "*"))
                .andExpect(header().string("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE"))
                .andExpect(header().string("Access-Control-Allow-Headers", "*"))
                .andExpect(header().string("Access-Control-Max-Age", "3600"));
    }

    public static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/
}
