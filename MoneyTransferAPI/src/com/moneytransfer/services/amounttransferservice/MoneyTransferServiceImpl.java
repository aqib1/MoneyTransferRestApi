package com.moneytransfer.services.amounttransferservice;

import java.util.Objects;

import com.moneytranfer.dto.AccountDto;
import com.moneytranfer.dto.MoneyTranferDto;
import com.moneytransfer.services.accountservice.AccountServiceImpl;
import com.moneytransfer.utility.DataUtils;
import com.moneytransfer.utility.TransactException;

public class MoneyTransferServiceImpl implements MoneyTransferService {

	private static volatile MoneyTransferServiceImpl moneyTransferServiceImpl = null;
	private AccountServiceImpl accountServiceImpl = AccountServiceImpl.getInstance();

	private MoneyTransferServiceImpl() {

	}

	@Override
	public void moneyTransfer(MoneyTranferDto moneyTranferDto) throws TransactException {
		AccountDto from = DataUtils.getInstance().getAccountList().stream()
				.filter(x -> x.getAccountId().equals(moneyTranferDto.getFromAccount().getAccountId())).findAny()
				.orElse(null);
		AccountDto to = DataUtils.getInstance().getAccountList().stream()
				.filter(x -> x.getAccountId().equals(moneyTranferDto.getToAccount().getAccountId())).findAny()
				.orElse(null);
		if (Objects.isNull(from) || Objects.isNull(to)) {
			throw new TransactException("Account details not provided [Please see to or from accounts]", null);
		}
		if (moneyTranferDto.getFromAccount().getBalance() <= 0) {
			throw new TransactException("Invalid amount to transfer", null);
		}
		if (from.getBalance() < moneyTranferDto.getFromAccount().getBalance()) {
			throw new TransactException("There is no enough balance in account to transfer", null);
		}
		try {
			from.setBalance(from.getBalance() - moneyTranferDto.getFromAccount().getBalance());
			to.setBalance(to.getBalance() + moneyTranferDto.getFromAccount().getBalance());
			accountServiceImpl.updateAccount(from);
			accountServiceImpl.updateAccount(to);
		} catch (Exception e) {
			throw new TransactException("General Exception occurs while updating account in database", e);
		}
	}

	public static MoneyTransferServiceImpl getInstance() {
		if (moneyTransferServiceImpl == null) {
			synchronized (MoneyTransferServiceImpl.class) {
				if (moneyTransferServiceImpl == null) {
					moneyTransferServiceImpl = new MoneyTransferServiceImpl();
				}
			}
		}
		return moneyTransferServiceImpl;
	}
}
