package com.moneytransfer.services.amounttransferservice;

import com.moneytransfer.dto.MoneyTranferDto;
import com.moneytransfer.utility.TransactException;

public interface MoneyTransferService {

	void moneyTransfer(MoneyTranferDto moneyTranferDto) throws TransactException;
}
