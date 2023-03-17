package com.FawryProject.FawryProject.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CustomerServiceProvider 
{
	ServiceDataAccess SDA =new ServiceDataAccess();
	
	protected List<String> Search(String str) throws SQLException
	{
		
		return SDA.Search(str);
	}

	
	public List<String> checkDiscounts() throws SQLException
	{	
        return SDA.checkDiscounts();
	}
	
}
