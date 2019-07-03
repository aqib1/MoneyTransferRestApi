package com.moneytransfer.services.amounttransferservice;

import com.moneytranfer.dto.MoneyTranferDto;
import com.moneytransfer.utility.TransactException;

public interface MoneyTransferService {

	void moneyTransfer(MoneyTranferDto moneyTranferDto) throws TransactException;
}
