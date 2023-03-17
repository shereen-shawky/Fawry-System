package com.FawryProject.FawryProject.Service;

public class ServiceProvideDataConvert 
{
	public String toString(Services s)
    {
    	return "Service Provider: "+ s.SName + " Service: "+ s.CName ;
    }
    
    public String toStringDis(Services s)
    {
    	return "Service Provider: "+ s.SName + " Service: "+ s.CName + " Discount: "+ s.d +'%' ;
    }

}
