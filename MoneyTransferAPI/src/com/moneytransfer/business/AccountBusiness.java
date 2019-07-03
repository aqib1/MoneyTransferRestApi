package com.moneytransfer.business;

import java.util.Objects;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.moneytransfer.dto.AccountDto;
import com.moneytransfer.services.accountservice.AccountServiceImpl;
import com.moneytransfer.utility.TransactException;

public class AccountBusiness {

	private static volatile AccountBusiness accountBusiness = null;

	private AccountBusiness() {

	}

	public Response getAllAccounts() {
		try {
			return Response.status(Status.OK).entity(AccountServiceImpl.getInstance().getAllAccounts())
					.type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response getAccountByAccountId(String accountId) {
		try {
			return Response.status(Status.OK).entity(AccountServiceImpl.getInstance().getAccountByAccountId(accountId))
					.type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response addAccount(AccountDto accountDto) {
		try {
			AccountServiceImpl.getInstance().addAccount(accountDto);
			return Response.status(Status.OK).entity(accountDto).type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response deleteAccount(String accountId) {
		try {
			AccountServiceImpl.getInstance().deleteAccount(accountId);
			return Response.status(Status.OK).entity("Account with accountId : "+accountId+" deleted successfully").type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response updateAccount(AccountDto accountDto) {
		try {
			if(Objects.isNull(accountDto.getAccountId()) || accountDto.getAccountId().isEmpty())
				return Response.status(Status.EXPECTATION_FAILED).entity(new IllegalArgumentException("account-id required to update specific account.")).build();
			AccountServiceImpl.getInstance().updateAccount(accountDto);
			return Response.status(Status.OK).entity(accountDto).type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public static AccountBusiness getInstance() {
		if (accountBusiness == null) {
			synchronized (AccountBusiness.class) {
				if (accountBusiness == null) {
					accountBusiness = new AccountBusiness();
				}
			}
		}
		return accountBusiness;
	}

}
