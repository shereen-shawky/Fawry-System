package com.FawryProject.FawryProject.Customer;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/customer")
public class CustomerController 
{
	CustomerService cs;
	
	@Autowired
	public CustomerController(CustomerService cs)
	{
		this.cs = cs;
	}
	
	@PostMapping("/SignUp")
	public String SignUp(@RequestBody Customer c) throws SQLException
	{
		return cs.SignUp(c);
	}
	
	@PostMapping("/SignIn")
	public String SignIn(@RequestBody Customer c) throws SQLException
	{
		return cs.SignIn(c);
	}
	@PutMapping("/SignOut")
	public String SignOut()
	{
		return cs.SignOut();
	}
	
}
