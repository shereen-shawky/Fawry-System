package com.FawryProject.FawryProject.Wallet;

import java.sql.SQLException;
import org.springframework.stereotype.Service;
import com.FawryProject.FawryProject.Customer.Customer;

@Service
public class WalletService 
{
	WalletDataAccess WDA = new WalletDataAccess();
	
	public String addFunds(int Amount) throws SQLException
	{
		if(!Customer.loggedIn)
		{
			return "Please log in First";
		}
		Wallet.balance += Amount;
		WDA.Addfunds(Amount);
		return "Funds added successfully";
	}
	
	public boolean Pay(double a) throws SQLException 
	{
		if(a<=Wallet.balance)
		{
			Wallet.balance -= a;
			WDA.pay(a);
			return true;
		}
		else
		{
			return false;
		}
		
	}

}
