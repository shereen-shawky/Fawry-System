package com.FawryProject.FawryProject.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.FawryProject.FawryProject.DatabaseConnection;
import com.FawryProject.FawryProject.Wallet.Wallet;

public class CustomerDataAccess 
{
	protected DatabaseConnection connection = new DatabaseConnection();
	protected Connection con ;
	protected Statement stmt;
	protected String query;
	protected ResultSet r;
	protected PreparedStatement ps;
	
	
	public int CreateAccount(Customer c) throws SQLException 
	{
		con=connection.connect();
		stmt = con.createStatement();
		query = "insert into UserAccounts "
		 		+ "(UserName, Email, Password) "
		 		+ "values(  " + '"' + c.UserName + '"' + ',' 
		 		+ '"' + c.Email + '"' + ',' 
		 		+ '"' + c.Password + '"' + ')';
		 int rs = stmt.executeUpdate(query);
		 con.close();
		 return rs;
	}

	public Customer CheckSignUpInputs(Customer c) throws SQLException 
	{
		con = connection.connect();
		stmt = con.createStatement();
		
		Customer c2 = new Customer();
		
		query = "select * from UserAccounts where UserName = "+'"'+ c.UserName + '"';
		r = stmt.executeQuery(query);
		
		if(!r.next())
		{
			query = "select * from UserAccounts where Email = "+'"'+ c.Email + '"';
			 r = stmt.executeQuery(query);
			 if(!r.next())
				{
				 	con.close();
				 	return null;
				}
			 else
				{
				 	r = stmt.executeQuery(query);
					c2.Email = r.getString("Email");
					c2.Email=r.getString("Email");
				 	con.close();
					return c2;
				}
		}
		else
		{
			r = stmt.executeQuery(query);
			c2.UserName = r.getString("UserName");
			c2.Email=r.getString("Email");
			con.close();
			return c2;
		}

	}

	public String CheckSignInInputs(Customer c) throws SQLException 
	{
		con = connection.connect();
		stmt = con.createStatement();
		
		
		query = "select * from UserAccounts where Email = "+'"'+ c.Email + '"' +';';
		r = stmt.executeQuery(query);
		if(r.next())
		{
			query = "select Password from UserAccounts where Email = "+'"'+ c.Email + '"';
			r = stmt.executeQuery(query);
			
			r.next();
			if(c.Password.equals(r.getString(1)))
			{
				query = "select ID from UserAccounts where Email = "+'"'+ c.Email + '"';
				r = stmt.executeQuery(query);
				r.next();
				Customer.id = r.getInt(1);
				
				query = "select UserName from UserAccounts where Email = "+'"'+ c.Email + '"';
				r = stmt.executeQuery(query);
				r.next();
				c.UserName = r.getString(1);
				
				
				
				query = "select * from UserAccounts "
						+ "where ID = " + Customer.id + ';';
				r = stmt.executeQuery(query);
				
				r.next();
				double balance = r.getDouble("WalletBalance");
				Customer.nOfTransactions = r.getInt("TransactionNum");
				
				Customer.w = new Wallet(balance);
				Customer.loggedIn=true;
				con.close();
				return "Logged In";
			}
			else
			{
				con.close();
				return "Password isn't correct";
			}
		}
		con.close();
		return "Email not found";
	}

}
