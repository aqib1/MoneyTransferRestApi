package com.moneytranfer.utility;

import java.time.LocalDate;

import com.moneytranfer.dto.AccountDto;

public class IAccountHelper {
	
	private IAccountHelper() {
		
	}
	
	public static void updateAccount(AccountDto old, AccountDto newAcc) {
		old.setAccountId(newAcc.getAccountId());
		old.setAccountName(newAcc.getAccountName());
		old.setBalance(newAcc.getBalance());
		old.setAccountHolder(newAcc.getAccountHolder());
		old.setCurrencyCode(newAcc.getCurrencyCode());
		old.setLastModifiedAt(LocalDate.now().toString());
		old.setAnnualInterestRate(newAcc.getAnnualInterestRate());
		
	}
}
