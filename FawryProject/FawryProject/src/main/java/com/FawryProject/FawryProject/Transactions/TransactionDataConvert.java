package com.FawryProject.FawryProject.Transactions;

public class TransactionDataConvert 
{
	public String toString(Transaction t)
	{
		return "ID: "+t.id+"     Service: "+t.s.CName+" Service Provider:" + t.s.SName + " Amount: "+ t.amount;
	}
	
	public String toStringUID(Transaction t)
	{
		return "ID: "+t.id+"     "+t.s.SName +" " + t.s.CName 
				+" User ID: "+ t.UID+ " Amount: "+ t.amount;
	}
}
