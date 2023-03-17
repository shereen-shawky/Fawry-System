package com.FawryProject.FawryProject.Service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.FawryProject.FawryProject.Customer.Customer;
import com.FawryProject.FawryProject.Wallet.WalletService;

@Service
public class CustomerServicePayment 
{
	ServiceDataAccess SDA =new ServiceDataAccess();
	
	public List<String> ViewServices() throws SQLException
	{	
        return SDA.Search("");
	}

	
	public Services getService(Services s) throws SQLException
	{
		return SDA.getService(s);
	}
	
	
	public String pay(Services serv2) throws NumberFormatException, SQLException
	{
		Services serv = new Services();
		double amount = 0;
		String res = "";
		if(!Customer.loggedIn)
		{
			return "Please Sign In First";
		}
		else
		{
			if(serv2.CName==null)
			{
				return "Please choose the service";
			}
			else if(serv2.SName==null)
			{
				return "Please choose the Service provider";
			}
			
			
			serv.CName=serv2.CName;
			serv.SName=serv2.SName;
			
			
			serv = getService(serv);
			serv = SDA.getDiscounts(serv);
			
			serv.formHandler=serv2.formHandler;
			
			if(serv.formHandler[0]==null || Double.parseDouble(serv.formHandler[0])<=0)
			{
				return "Please Enter the amount you are willing to pay";
			}
			else if(serv.formHandler[1]==null || (!serv.formHandler[1].equals("Cash") && !serv.formHandler[1].equals("Credit")  && !serv.formHandler[1].equals("Wallet")))
			{
				return "Please Enter the payment option";
			}
			boolean flag = true;
			
			for(int i=0; i<serv.nOfFields; i++)
			{
				if(serv.formHandler[i]==null)
				{
					flag = false;
					break;
				}
			}
			if(!flag)
			{
				return "Please fill all form queries";
			}
			else
			{
				amount=Double.parseDouble(serv.formHandler[0]);
				if (serv.HasDiscount)
				{
					amount = amount - (serv.d*amount)/100;
					res = "You have "+serv.d+"% discount, you'll only pay "+amount+"\n";
				}
				if(SDA.getOverallDiscount()!=0 && Customer.nOfTransactions==0)
				{
					amount = amount - (SDA.getOverallDiscount()*Double.parseDouble(serv.formHandler[0]))/100;
					res += "You have "+SDA.getOverallDiscount()+"% overall discount, you'll only pay "+amount+"\n";
				}
				
				
				if(serv.formHandler[1].equals("Cash") || serv.formHandler[1].equals("Credit"))
				{
					SDA.MakeTransaction(serv,amount);
					 Customer.nOfTransactions++;
					 res+="Transaction Done";
					return res;
				}
				else
				{
					WalletService ws = new WalletService();
					if(ws.Pay(Double.parseDouble(serv.formHandler[0])))
					{
						SDA.MakeTransaction(serv,amount);
						Customer.nOfTransactions++;
						res+="Transaction Done";
						return res;
					}
					else
					{
						return "Balance isn't enough";
					}
				}
			}
			
		}
	}

	

}
