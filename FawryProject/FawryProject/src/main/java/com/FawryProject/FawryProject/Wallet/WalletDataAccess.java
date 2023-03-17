package com.FawryProject.FawryProject.Wallet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.FawryProject.FawryProject.DatabaseConnection;
import com.FawryProject.FawryProject.Customer.Customer;

public class WalletDataAccess 
{
	protected DatabaseConnection connection = new DatabaseConnection();
	protected Connection con ;
	protected String query;
	protected PreparedStatement ps;
	protected Statement stmt;
	
	protected void pay(double a) throws SQLException
	{
		con = connection.connect();
	
		query = "update UserAccounts "
				+ "set walletbalance = "+ Wallet.balance 
				+ " where ID = " +Customer.id+ ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		con.close();	
	}
	
	protected void Addfunds(double Amount) throws SQLException
	{
		con = connection.connect();
		stmt = con.createStatement();
		
		query = "update UserAccounts "
				+ "set walletbalance = "+ Wallet.balance 
				+ " where ID = "+ Customer.id + ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		query = "insert into Transactions "
				 + "(Service, Category, UserID, Amount) "
				 + "values(  " 
				 + "\"Adding Funds\"" + ',' 
				 + "\"To Wallet\"" + ',' 
				 + '"' + Customer.id + '"' + ','
				 + '"' + Amount + '"' + ')'+';';
		stmt.executeUpdate(query);
		con.close();
	}

}
