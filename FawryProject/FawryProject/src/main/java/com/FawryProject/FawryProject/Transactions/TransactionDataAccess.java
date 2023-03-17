package com.FawryProject.FawryProject.Transactions;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FawryProject.FawryProject.DatabaseConnection;
import com.FawryProject.FawryProject.Customer.Customer;
import com.FawryProject.FawryProject.Service.Services;

public class TransactionDataAccess 
{
	protected DatabaseConnection connection = new DatabaseConnection();
	protected Connection con ;
	protected Statement stmt;
	protected String query;
	protected ResultSet r;
	protected PreparedStatement ps;
	
	TransactionDataConvert tdc = new TransactionDataConvert();
	Transaction t = new Transaction();
	
	protected List<String> getTransactions() throws SQLException
	{
	
		List<String> list = new ArrayList<String>();
		
		con = connection.connect();
		
		query = "select id,service,category,amount from Transactions where UserID = " 
		+ '"' + Customer.id + '"' + "and type = \"Payment\" ;";
        ps = con.prepareStatement(query);
        r = ps.executeQuery();

        
        if(!r.next())
        {
        	con.close();
        	return List.of("you have not made any transactions yet");
        }
        
        r = ps.executeQuery();
        
        while (r.next()) 
        {

             t.id = r.getInt("id");
             t.s = new Services();
             t.s.SName = r.getString("service");
             t.s.CName = r.getString("category");
             t.amount = r.getDouble("amount");
            
            list.add(tdc.toString(t));
        }
        con.close();
        return list;
	}

	protected String AskForRefund(int id) throws SQLException
	{
		con = connection.connect();		
		query = "select * from Transactions where UserID = " + '"' + Customer.id + '"' 
        		+" AND ID = " + '"' + id + '"' +';';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();

        if(!r.next())
        {
        	con.close();
        	return "Wrong Transaction ID";
        }
        query = "update Transactions "
				+ "set Refund = '1'"  
				+ "where ID = "+ id + ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
		return "Request Placed Successfully";
	}

	protected List<String> ViewRefundRequest() throws SQLException
	{	
		con = connection.connect();		
		query = "select * from Transactions where Refund = 1 ";
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        
        List<String> list = new ArrayList<String>();
        
        if(!r.next())
        {
        	con.close();
        	return List.of("No refund Requests");
        }
        
        r = ps.executeQuery();

        while (r.next()) 
        {
            t.id = r.getInt("id");
            t.s=new Services();
            t.s.SName = r.getString("service");
            t.s.CName = r.getString("category");
            t.UID = r.getInt("UserId");
            t.amount = r.getDouble("amount");
            
          list.add(tdc.toStringUID(t));
        }
        con.close();
        return list;
	}

	protected String AcceptRefundRequest(int id) throws SQLException
	{
		con = connection.connect();
		
		query = "select id from Transactions where ID = " + '"' + id + '"' 
        		+"and refund = 1" + ';';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        
        if(!r.next())
        {
        	con.close();
        	return "Wrong Transaction ID";
        }
        
        
        query = "select userid from Transactions where ID = " + '"' + id + '"';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
		
        r.next();
        int i = r.getInt("userid");
        
        query = "select walletbalance from UserAccounts where ID = " + '"' + i + '"';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        
        r.next();
        double b = r.getDouble("walletbalance");
        
        query = "select amount from Transactions where ID = " + '"' + id + '"';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        
        r.next();
        double a = r.getDouble("amount");
        
        query = "update UserAccounts "
				+ "set walletbalance = " + '"' + (b + a) + '"'   
				+ "where ID = "+ i + ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		query = "delete from Transactions "
				+ "where ID = "+ id + ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		query = "insert into Transactions "
				 + "(Service, Category, UserID, Amount) "
				 + "values(  " 
				 + "\"Accepted\"" + ',' 
				 + "\"Refund\"" + ',' 
				 + '"' + Customer.id + '"' + ','
				 + '"' + a + '"' + ')'+';';
		stmt = con.createStatement();
		stmt.executeUpdate(query);
		
		con.close();
		return "Refund Done Successfully";
        
	}

	public List<String> GetAllTransactions() throws SQLException 
	{
		con = connection.connect();		
		query = "select * from Transactions";
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        
        List<String> list = new ArrayList<String>();
        
        if(!r.next())
        {
        	con.close();
        	return List.of("No refund Requests");
        }
        
        r = ps.executeQuery();

        while (r.next()) 
        {
            t.id = r.getInt("id");
            t.s=new Services();
            t.s.SName = r.getString("service");
            t.s.CName = r.getString("category");
            t.UID = r.getInt("UserId");
            t.amount = r.getDouble("amount");
            
          list.add(tdc.toStringUID(t));
        }
        con.close();
        return list;
	}
}
