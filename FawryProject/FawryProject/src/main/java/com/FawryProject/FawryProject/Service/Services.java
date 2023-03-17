package com.FawryProject.FawryProject.Service;

public class Services 
{
    public  String SName, CName;
    public  int d;
    public  String form[], formHandler[]; 
    public  int nOfFields;
    public boolean HasDiscount;
    
	public void setFrom(String str) 
	{
		form = new String[nOfFields];
		formHandler = new String[nOfFields];
		form[0]="";
		
		for(int i=0,j=0; i<str.length(); i++)
		{
			if(str.charAt(i)!='@')
			{
				form[j]+=str.charAt(i);
			}
			else
			{
				j++;
				form[j]="";
			}
		}
		
	}
}
