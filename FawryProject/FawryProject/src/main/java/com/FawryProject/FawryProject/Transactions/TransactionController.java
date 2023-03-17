package com.FawryProject.FawryProject.Transactions;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Transactions")
public class TransactionController 
{
	CustomerTransactionService ts;
	AdminTransactionService ts2;
	
	@Autowired
	public TransactionController(CustomerTransactionService ts, AdminTransactionService ts2)
	{
		this.ts=ts;
		this.ts2=ts2;
	}
	
	@GetMapping("/GetMyTransactions")
	public List<String> GetTransactions() throws SQLException
	{
		return ts.getTransactions();
	}
	
	@PutMapping("/RefundRequest/{id}")
	public String AskForRefund(@PathVariable int id) throws SQLException
	{
		return ts.askForRefund(id);
	}
	
	@GetMapping("/GetRefundRequests")
	public List<String> GetRefundRequests() throws SQLException
	{
		return ts2.ViewRefundRequest();
	}
	
	@PutMapping("/AcceptRefund/{id}")
	public String AcceptRefund(@PathVariable int id) throws SQLException
	{
		return ts2.AcceptRefundRequest(id);
	}
	@GetMapping("/GetAllTransactions")
	public List<String> GetAllTransactions() throws SQLException
	{
		return ts2.GetAllTransactions();
	}
	
	
}
