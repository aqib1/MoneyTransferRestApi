package com.moneytransfer.services.accountservice;

import java.util.List;

import com.moneytranfer.dto.AccountDto;
import com.moneytransfer.utility.DataUtils;
import com.moneytransfer.utility.IAccountHelper;
import com.moneytransfer.utility.TransactException;

public class AccountServiceImpl implements AccountService {

	private static volatile AccountServiceImpl accountServiceImpl = null;

	private AccountServiceImpl() {

	}

	// General - Exception handling in the case if you replace in memory data with
	// actual database
	@Override
	public List<AccountDto> getAllAccounts() throws TransactException {
		synchronized (AccountServiceImpl.class) {
			try {
				return DataUtils.getInstance().getAccountList();
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while fetching account-list form database", e);
			}
		}
	}

	@Override
	public AccountDto getAccountByAccountId(String accountId) throws TransactException {
		synchronized (AccountServiceImpl.class) {
			try {
				return DataUtils.getInstance().getAccountList().stream().filter(x -> x.getAccountId().equals(accountId))
						.findFirst().orElse(null);
			} catch (Exception e) {
				throw new TransactException(
						"General Exception occurs while fetching account by accountId form database", e);
			}
		}
	}

	@Override
	public void addAccount(AccountDto accountDto) throws TransactException {
		synchronized (AccountServiceImpl.class) {
			try {
				if (DataUtils.getInstance().getAccountList().stream()
						.anyMatch(x -> x.getAccountId().equals(accountDto.getAccountId()))) {
					throw new TransactException("Data replication -> accountId : " + accountDto.getAccountId()
							+ " already exists in database", null);
				}
				DataUtils.getInstance().getAccountList().add(accountDto);
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while adding account in database", e);
			}
		}
	}

	@Override
	public void updateAccount(AccountDto accountDto) throws TransactException {
		synchronized (AccountServiceImpl.class) {
			try {
				DataUtils.getInstance().getAccountList().stream().forEach(x -> {
					if (x.getAccountId().equals(accountDto.getAccountId())) {
						IAccountHelper.updateAccount(x, accountDto);
					}
				});
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while updating account in database", e);
			}
		}
	}

	@Override
	public void deleteAccount(String accountId) throws TransactException{
		synchronized (AccountServiceImpl.class) {
			try {
				DataUtils.getInstance().getAccountList()
						.removeIf(c -> c.getAccountId().equals(accountId));
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while deleting account in database", e);
			}
		}
	}

	public static AccountServiceImpl getInstance() {
		synchronized (AccountServiceImpl.class) {
			if (accountServiceImpl == null) {
				synchronized (AccountServiceImpl.class) {
					accountServiceImpl = new AccountServiceImpl();
				}
			}

		}
		return accountServiceImpl;
	}

}
