package com.employeesystem.service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.employeesystem.dao.EmployeeDao;
import com.employeesystem.models.Employee;
import com.employeesystem.repositories.EmployeeRepository;

import java.util.List;
import java.util.Optional;
@Service
public class EmployeeService {
	@Autowired
	private EmployeeDao edao;
	@Autowired
	private EmployeeRepository erpo;
	
	public List getAllEmployee(){
		//Iterable<Employee> eiterable=(Iterable<Employee>) edao.getAll();
		List elist=edao.getAll();
		//eiterable.forEach(em->elist.add(em));
		
		return elist;
	}
	public Employee postEmployee(Employee emp) {
		/*
		 * Employee emp=new Employee(); emp.setId(4); emp.setName("charan");
		 * emp.setEmail("charan@gmail.com"); emp.setCompany_name("infoys");
		 */
		System.out.println("empservice entered");
		if(!erpo.existsById(emp.getId())) {
			erpo.save(emp);
			
		}
		return emp;
	}
	public Employee getEmployee(int id) {
		Employee emp=null;
		if(erpo.existsById(id)) {
		 emp=edao.getEmployee(id);
		}
		return emp;
	}
	public List getsomeEmployee(int from,int to) {
		List l=(List) erpo.findAll();
		
		List<Employee> someemp=(List) l.stream().filter(emp->(((Employee) emp).getId()>=from && ((Employee) emp).getId()<=to)).collect(Collectors.toList());
		//System.out.println(someemp);
		return someemp;
	}
}
