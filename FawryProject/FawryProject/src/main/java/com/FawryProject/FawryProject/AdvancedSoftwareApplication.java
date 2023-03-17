package com.FawryProject.FawryProject;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AdvancedSoftwareApplication 
{
	
	protected static DatabaseConnection connection = new DatabaseConnection();
	protected static Connection con ;
	protected static Statement stmt;
	
	public static void main(String[] args) throws SQLException {
		SpringApplication.run(AdvancedSoftwareApplication.class, args);
	}

}
