package com.employeesystem.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Arrays;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.employeesystem.dao.EmployeeDao;
import com.employeesystem.models.Employee;
import com.employeesystem.repositories.EmployeeRepository;

class EmployeeServiceTest {
	@InjectMocks
	EmployeeService empservice;
	@Mock
	EmployeeRepository emprpo;
	@Mock
	EmployeeDao empdao;
	@BeforeEach
	public void init() {
		
		MockitoAnnotations.initMocks(this);
					
	}
	@Test
	void testGetAllEmployees() {
		//employee 1
		Employee emp=new Employee();
		emp.setId(1);
		emp.setName("sai kumar");
		emp.setEmail("saikumar@gmail.com");
		emp.setCompany_name("globallogic");
		//employee 2
		Employee emp2=new Employee();
		emp2.setId(2);
		emp2.setName("kiranmayi");
		emp2.setEmail("kiranmayi@gmail.com");
		emp2.setCompany_name("globallogic");
		//employee 3
		Employee emp3=new Employee();		
		emp3.setId(3);
		emp3.setName("priya kumari");
		emp3.setEmail("priya@gmail.com");
		emp3.setCompany_name("globallogic");
		Employee[] emparray={emp,emp2,emp3};
		List<Object> emplist=Arrays.asList(emparray);
		when(empdao.getAll()).thenReturn(emplist);
		List<Object> result=empservice.getAllEmployee();
		//test 1
		assertEquals(emp3.getId(), ((Employee)result.get(2)).getId());
		//test 2
		assertNotNull(result);
		//test 3
		assertEquals(emplist.size(), result.size());
		//test 4
		assertEquals(emp2.getEmail(), ((Employee)result.get(1)).getEmail());
		//test 5
		assertEquals(emp3.getCompany_name(), ((Employee)result.get(2)).getCompany_name());
		
		//test 5
		assertAll("result",()->{
				assertNotNull(result.get(0));},
				()->{
					assertNotNull(result.get(1));},
				()->{
					assertNotNull(result.get(2));}
		);
	}
	
	//test case 1 for the postemployee method
	@Test
	void testPostEmployee() {
		Employee emp=new Employee();
		emp.setId(7);
		emp.setName("priya kumari");
		emp.setEmail("priya@gmail.com");
		emp.setCompany_name("globallogic");
		when(emprpo.existsById(emp.getId())).thenReturn(false);
		Employee result=empservice.postEmployee(emp);
		assertAll("result",
		()->{assertNotNull(result);},
		()->{assertTrue(result.getId()==emp.getId());},
		()->{assertTrue(result.getEmail().equalsIgnoreCase(emp.getEmail()));}
		);
		
	}
	//test case 2 for the postemploye method
	@Test
	void testPostEmployee2() {
		Employee emp=new Employee();
		emp.setId(10);
		emp.setName("priya kumar");
		emp.setEmail("priyakumar@gmail.com");
		emp.setCompany_name("globallogic1");
		when(emprpo.existsById(emp.getId())).thenReturn(true);
		Employee result=empservice.postEmployee(emp);
		assertEquals("employee alredy registed", result);
		
	}
	
	//test case 1 for getemployee method in EmployeeService 
	@Test
	void testgetEmployee() {
		Employee emp=new Employee();
		emp.setId(3);
		emp.setName("priya kumari");
		emp.setEmail("priya@gmail.com");
		emp.setCompany_name("globallogic");
		//when(emprpo.existsById(3)).thenReturn(true);
		Optional<Employee> empo=null;
		
		//passing duplicate data
		when(emprpo.existsById(3)).thenReturn(true);
		when(empdao.getEmployee(3)).thenReturn(emp);
		//calling getEmployee service method from the EmployeeService class
		Employee actual=empservice.getEmployee(3);
		
		assertNotNull(actual);
		assertEquals(emp.getEmail(), actual.getEmail());
		assertEquals(emp.getName(), actual.getName());
	}
	
	//test case 2 for getemployee method in EmployeeService 
		@Test
		void test2getEmployee() {
			Employee emp=new Employee();
			emp.setId(3);
			emp.setName("priya kumari");
			emp.setEmail("priya@gmail.com");
			emp.setCompany_name("globallogic");
			//when(emprpo.existsById(3)).thenReturn(true);
			Optional<Employee> empo=null;
			
			//passing duplicate data
			when(emprpo.existsById(3)).thenReturn(false);
			when(empdao.getEmployee(3)).thenReturn(null);
			//calling getEmployee service method from the EmployeeService class
			Employee actual=empservice.getEmployee(3);
			
			//testing whether actual object is null or not
			assertNull(actual);
			
		}
		//Test case for getsomeEmployees in EmployeeService class
		@Test
		public void testgetSomeEmployees() {
			//employee 1
			Employee emp=new Employee();
			emp.setId(1);
			emp.setName("sai kumar");
			emp.setEmail("saikumar@gmail.com");
			emp.setCompany_name("globallogic");
			//employee 2
			Employee emp2=new Employee();
			emp2.setId(2);
			emp2.setName("kiranmayi");
			emp2.setEmail("kiranmayi@gmail.com");
			emp2.setCompany_name("globallogic");
			//employee 3
			Employee emp3=new Employee();		
			emp3.setId(3);
			emp3.setName("priya kumari");
			emp3.setEmail("priya@gmail.com");
			emp3.setCompany_name("globallogic");
			Employee[] emparray={emp,emp2,emp3};
			List<Employee> emplist=Arrays.asList(emparray);
			when(emprpo.findAll()).thenReturn(emplist);
			List<Employee> allemps=emprpo.findAll();
			//calling the getsomeemployees method from EmployeeService class
			List<Employee> someemps=empservice.getsomeEmployee(1,3);
			
			assertNotNull(someemps);
			
			assertTrue(allemps.size()==someemps.size());
			
			assertAll("someemps",()->{	
			assertTrue(someemps.get(0).getId()==emp.getId());},
			()->{	
				assertTrue(someemps.get(0).getName().equalsIgnoreCase(emp.getName()));},
			()->{	
				assertTrue(someemps.get(0).getEmail().equalsIgnoreCase(emp.getEmail()));},
			()->{	
				assertTrue(someemps.get(0).getCompany_name().equalsIgnoreCase(emp.getCompany_name()));}
			);
			
		}
		
		

}
