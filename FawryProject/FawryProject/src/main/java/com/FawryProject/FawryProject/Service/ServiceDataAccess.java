package com.FawryProject.FawryProject.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.FawryProject.FawryProject.DatabaseConnection;
import com.FawryProject.FawryProject.Customer.Customer;

public class ServiceDataAccess 
{
	protected DatabaseConnection connection = new DatabaseConnection();
	protected Connection con ;
	protected Statement stmt;
	protected String query;
	protected ResultSet r;
	protected PreparedStatement ps;
	
	ServiceProvideDataConvert SDC = new ServiceProvideDataConvert();
	
	public Services getDiscounts(Services s) throws SQLException
    {
		Connection con = connection.connect();
		stmt = con.createStatement();
		
		query = "select Discount from services "
				+ "where sname = " + '"' +s.SName + '"'
				+ "And cname =" + '"' + s.CName + '"'
				+ "And HasDiscount = '1'";
		r = stmt.executeQuery(query);
		if(r.next())
		{
			s.HasDiscount=true;
			s.d = r.getInt(1);
		
		}
		con.close();
		return s;
    }
	
	protected int getOverallDiscount() throws SQLException
	{
		con = connection.connect();
		stmt = con.createStatement();
		query = "select Discount from services "
				+ "where sname = 'Overall'"
				+ "And cname = 'Discount' "
				+ "And HasDiscount = '1'";
		r = stmt.executeQuery(query);
		if(r.next())
		{
			int k = r.getInt(1);
			con.close();
			return k;
		}
		con.close();
		return 0;
	}

	protected List<String> Search(String str) throws SQLException
	{
		Services s = new Services();
		List<String> list = new ArrayList<String>();
		con = connection.connect();

		query = "select * from services where sname like " + '"'+'%' + str + '%'+'"'+"AND Sname NOT IN('Overall')"  +';';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();

        if(!r.next())
        {
        	con.close();
        	return null;
        }
        r=ps.executeQuery();
        
        while (r.next()) 
        {
            s.SName = r.getString("sname");
            s.CName = r.getString("cname");
            
            list.add(SDC.toString(s));
        }
        con.close();
		return list;
	}

	protected List<String> checkDiscounts() throws SQLException
	{	
		Services serv = new Services();
		List<String> list = new ArrayList<String>();
		
		con = connection.connect();
		stmt = con.createStatement();
		
		query = "select Sname,Cname,Discount from services where HasDiscount = 1 AND Sname NOT IN('Overall')";
        ps = con.prepareStatement(query);
        r = ps.executeQuery();

        if(!r.next())
        {
        	con.close();
        	return List.of("No Discounts Currently");
        }
        
        r = ps.executeQuery();
        while (r.next()) 
        {
        	serv.SName	= r.getString("sname");
            serv.CName = r.getString("cname");
            serv.d = (int) r.getDouble("Discount");
            
            list.add(SDC.toStringDis(serv));
        }
        con.close();
        return list;
	}

	protected void MakeTransaction(Services serv,double amount) throws SQLException
	{
		con = connection.connect();
		stmt = con.createStatement();
		query = "insert into Transactions "
				 + "(Service, Category, UserID, Amount, type) "
				 + "values(  " 
				 + '"' + serv.SName + '"' + ',' 
				 + '"' + serv.CName + '"' + ',' 
				 + '"' + Customer.id + '"' + ','
				 + '"' + amount + '"' + ','
				 +"\"Payment\""+ ')'+';';
		
		stmt.executeUpdate(query);	
		
		query =  "select TransactionNum from UserAccounts "
				 + "where ID = " + '"'+ Customer.id+'"' + ";" ;
		r = stmt.executeQuery(query);
		r.next();
		int tn = r.getInt(1);
		
		query =  "Update UserAccounts Set TransactionNum = " + '"' + (tn+1) + '"'
				 + "where ID = " + '"'+ Customer.id+'"' + ";" ;
		stmt = con.createStatement();
		stmt.executeUpdate(query);
		con.close();
	}
	
	protected void AddSpecificDiscount(Services s) throws SQLException
	{
		con = connection.connect();
		stmt = con.createStatement();
	
		query = "update services "
				+ "set HasDiscount = '1'"
				+ "where Sname = "+ '"'+ s.SName+'"' +"AND Cname = "+'"'+ s.CName+'"'+ ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		query = "update services "
				+ "set Discount ="+ '"'+ s.d+'"'
				+ "where Sname = "+ '"'+ s.SName+'"' +"AND Cname = "+'"'+s.CName+'"'+ ';' ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
	}

	protected void AddOverallDiscount(int d) throws SQLException
	{
		con = connection.connect();
		stmt = con.createStatement();
		query = "update services "
				+ "set HasDiscount = '1'"
				+ "where Sname = 'Overall' AND Cname = 'Discount';" ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		
		query = "update services "
				+ "set Discount ="+ '"'+d+'"'
				+ "where Sname = 'Overall' AND Cname = 'Discount';" ;
		ps = con.prepareStatement(query);
		ps.executeUpdate();
		con.close();
	}

	public Services getService(Services serv) throws SQLException 
	{
		con = connection.connect();
		stmt = con.createStatement();
		
		query = "select * from services where Sname = " + '"'+serv.SName+'"' + 
				"AND Cname =" + '"'+serv.CName+'"' +';';
        ps = con.prepareStatement(query);
        r = ps.executeQuery();
        if(!r.next())
        {
        	con.close();
        	return null;
        }
        r = ps.executeQuery();
        while (r.next()) 
        {
        	serv.SName	= r.getString("sname");
            serv.CName = r.getString("cname");
            serv.d = (int) r.getDouble("Discount");
            serv.nOfFields = r.getInt("noOfFields");
            String q = r.getString("queries");
            serv.setFrom(q);
        }
        con.close();
		return serv;
	}

	public String AddServiceProvider(Services serv,String q) throws SQLException 
	{
		con = connection.connect();
		stmt = con.createStatement();
		query = "insert into services "
				 + "(Sname, Cname, noOfFields, queries) "
				 + "values(  " 
				 + '"' + serv.SName + '"' + ',' 
				 + '"' + serv.CName + '"' + ',' 
				 + '"' + serv.nOfFields + '"' + ','
				 + '"' + q + '"' +')'+';';
		
		stmt.executeUpdate(query);	
		return "Service Provider added";
	}
}
