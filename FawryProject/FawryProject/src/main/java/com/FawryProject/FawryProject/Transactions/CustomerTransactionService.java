package com.FawryProject.FawryProject.Transactions;

import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Service;
import com.FawryProject.FawryProject.Customer.Customer;


@Service
public class CustomerTransactionService 
{

	Transaction t = new Transaction();
	TransactionDataAccess TDA = new TransactionDataAccess();
	
	public List<String> getTransactions() throws SQLException
	{
		if(!Customer.loggedIn)
		{
			return List.of("Please log in first");
		}
		return TDA.getTransactions();
	}
	
	public String askForRefund(int id) throws SQLException
	{
		if(!Customer.loggedIn)
		{
			return "Please log in first";
		}
		
        return TDA.AskForRefund(id);
	}

}
