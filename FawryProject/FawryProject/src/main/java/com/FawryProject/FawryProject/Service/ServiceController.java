package com.FawryProject.FawryProject.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Service")
public class ServiceController 
{
	AdminServiceProvider asp;
	CustomerServiceProvider csp;
	CustomerServicePayment cPay;
	
	@Autowired
	public ServiceController(AdminServiceProvider asp, CustomerServiceProvider csp, CustomerServicePayment cPay)
	{
		this.asp=asp;
		this.csp=csp;
		this.cPay=cPay;
	}
	
	@GetMapping("/Search/{s}")
	public List<String> Search(@PathVariable String s) throws SQLException
	{
		return csp.Search(s);
	}
	
	@GetMapping("/Discounts")
	public List<String> checkDiscounts() throws SQLException
	{
		return csp.checkDiscounts();
	}
	
	@GetMapping("/viewServices")
	public List<String> ViewServices() throws SQLException
	{
		return cPay.ViewServices();
	}
	
	@GetMapping("/GetServicesForm")
	public String[] GetService(@RequestBody Services s) throws SQLException
	{
		if (cPay.getService(s) == null)
		{
			String arr[] = new String[1];
			arr[0]="Wrong Service name";
			return arr;
		}
		return cPay.getService(s).form;
	}
	
	@PostMapping("/Pay")
	public String pay(@RequestBody Services s) throws NumberFormatException, SQLException
	{
		return cPay.pay(s);
	}
	
	@PostMapping("/AddSpecificDiscount")
	public String AddSpecificDiscount(@RequestBody Services s) throws SQLException
	{
		return asp.addSpecificDiscout(s);
	}
	
	@PostMapping("/AddOverallDiscount/{d}")
	public String AddOverallDiscount(@PathVariable int d) throws SQLException
	{
		return asp.AddOverallDiscount(d);
	}
	
	@PostMapping("/AddServiceProvider")
	public String AddServiceProvider(@RequestBody Services s) throws SQLException
	{
		return asp.AddServiceProvider(s);
	}
	
	
	
	
}
