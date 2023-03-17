package com.FawryProject.FawryProject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection 
{
	Connection con;
	Statement stmt;
	String url = "jdbc:mysql://mysql-100874-0.cloudclusters.net:10139?user=Yara&password=FawrySystem", 
			uname = "Yara",
			pass = "FawrySystem",
			path = "jdbc:sqlite:FawrySystem.db";
	
	public Connection connect() throws SQLException 
	{
	 //con = DriverManager.getConnection(url,uname,pass);
	 con = DriverManager.getConnection(path);
	 stmt = con.createStatement();
	 //String sqlusedb = "use FawrySystem";
     //stmt.executeUpdate(sqlusedb);
     	 
	 return con;
	}
}
