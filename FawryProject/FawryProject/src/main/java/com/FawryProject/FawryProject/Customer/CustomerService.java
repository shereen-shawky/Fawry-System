package com.FawryProject.FawryProject.Customer;

import java.sql.SQLException;
import org.springframework.stereotype.Service;

import com.FawryProject.FawryProject.Wallet.Wallet;

@Service
public class CustomerService 
{	
	CustomerDataAccess CDA = new CustomerDataAccess();

	public String SignUp(Customer c) throws SQLException
	{
		if(c.Email==null)
		{
			return "Plesae Enter your Email";
		}
		else if(c.UserName==null)
		{
			return "Please enter your User name";
		}
		else if(c.Password==null)
		{
			return "Please enter your password";
		}
		else
		{
			String res = CheckSignUpInputs(c); 
			if(res==null)
			{
				return CreateAccount(c);
			}
			else
			{
				return res;
			}
		}
	}
		
	private String CreateAccount(Customer c) throws SQLException 
	{
		 if(CDA.CreateAccount(c)==1)
		 {
			 return "Account Created Successfully";
		 }
		 else
		 {
			 return "Cannot Create Account";
		 }

	}

	public String CheckSignUpInputs(Customer c) throws SQLException 
	{
		Customer c2 = new Customer();
		c2 = CDA.CheckSignUpInputs(c);
		if(c2==null)
		{
			return CreateAccount(c);
		}
		if(c2.Email.equals(c.Email))
		{
			return "Username already used \n" + "Email: "+ c2.Email + '\n' + "Username: " + c2.UserName;
		}
		return "Email already used \n" + "Email: "+ c2.Email + '\n' + "Username: " + c2.UserName;

	}
	
	public String SignIn(Customer c) throws SQLException
	{
		if(c.Email==null)
		{
			return "Plesae Enter your Email";
		}
		else if(c.Password==null)
		{
			return "Please enter your password";
		}
		else
		{
			 return CheckSignInInputs(c); 
		}
	}

	private String CheckSignInInputs(Customer c) throws SQLException 
	{
		return CDA.CheckSignInInputs(c);
	}
	
	public String SignOut()
	{
		if(!Customer.loggedIn)
		{
			return "Please log in first";
		}
		Customer.loggedIn=false;
		Customer.id=0;
		Customer.nOfTransactions=-1;
		Wallet.balance =0;
		return "Logged Out";
	}
}
