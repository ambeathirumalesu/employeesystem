package com.employeesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.employeesystem.models.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
	
	/*
	 * @Query("from Employee emp where emp.id= :empid") public Employee
	 * findEmployee(@Param("id") int empid);
	 */
}
