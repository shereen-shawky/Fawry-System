package com.FawryProject.FawryProject.Service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;

@Service
public class AdminServiceProvider 
{
	ServiceDataAccess SDA =new ServiceDataAccess();
	public String addSpecificDiscout(Services s) throws SQLException
	{
		if(s.d<0)
		{
			return "Please enter the discount amount";
		}
		
		SDA.AddSpecificDiscount(s);

		return "Discount added Successfully";
	}
	
	
	public String AddOverallDiscount(int d) throws SQLException
	{
		if(d<0)
		{
			return "Please enter the discount amount";
		}
		
		SDA.AddOverallDiscount(d);
		
		return "Discount added Successfully";
	}


	public String AddServiceProvider(Services s) throws SQLException 
	{
		String q = "";
		for(int i=0; i<s.nOfFields-1; i++)
		{
			q+=s.form[i]+'@';
		}
		q+=s.form[s.nOfFields-1];
		
		
		return SDA.AddServiceProvider(s,q);
	}
	
}
