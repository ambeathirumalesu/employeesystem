package com.employeesystem.dao;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.employeesystem.models.Employee;
import com.employeesystem.repositories.EmployeeRepository;

@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository erepo;
	public List getAll(){
		
		/*
		 * Employee e=new Employee(); e.setId(1); e.setName("thirumalesu");
		 * e.setEmail("thiru@gamil.com"); e.setCompany_name("globallogic");
		 */
		List<Employee> elist = (List<Employee>) erepo.findAll();
		//elist.add(e);
		return elist;
	}
	
	
	public Employee getEmployee(int id) {
		return erepo.findById(id).get();
	}
}
