package com.FawryProject.FawryProject.Wallet;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/Wallet")
public class WalletController 
{
	WalletService walletserv;
	
	@Autowired
	public WalletController(WalletService ws)
	{
		this.walletserv = ws;
	}
	
	@PutMapping("/AddFunds/{Amount}")
	public String SignIn(@PathVariable int Amount) throws SQLException
	{
		return walletserv.addFunds(Amount);
	}
}
