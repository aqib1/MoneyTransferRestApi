package com.moneytransfer.services.accountservice;

import java.util.List;

import com.moneytranfer.dto.AccountDto;
import com.moneytranfer.utility.TransactException;

public interface AccountService {
	
	List<AccountDto> getAllAccounts() throws TransactException;
	
	AccountDto getAccountByAccountId(String accountId) throws TransactException;
	
	void addAccount(AccountDto accountDto) throws TransactException;
	
	void updateAccount(AccountDto accountDto) throws TransactException;
  
	void deleteAccount(String accountId) throws TransactException;
}
