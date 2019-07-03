package com.moneytransfer.services.amounttransferservice;

import com.moneytranfer.dto.MoneyTranferDto;
import com.moneytranfer.utility.TransactException;

public interface MoneyTransferService {

	void moneyTransfer(MoneyTranferDto moneyTranferDto) throws TransactException;
}
