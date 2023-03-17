package com.FawryProject.FawryProject.Transactions;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AdminTransactionService 
{
	Transaction t = new Transaction();
	TransactionDataAccess TDA = new TransactionDataAccess();
	public List<String> ViewRefundRequest() throws SQLException
	{	
        return TDA.ViewRefundRequest();
	}
	
	public String AcceptRefundRequest(int id) throws SQLException
	{	
		return TDA.AcceptRefundRequest(id);  
	}

	public List<String> GetAllTransactions() throws SQLException 
	{
		return TDA.GetAllTransactions();
	}
}
