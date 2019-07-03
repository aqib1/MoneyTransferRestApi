package com.moneytransfer.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.moneytranfer.dto.AccountDto;
import com.moneytransfer.business.AccountBusiness;

@Path("/accounts")
public class AccountController {
	private AccountBusiness accountBusiness = AccountBusiness.getInstance();

	@GET
	@Path("/all")
	public Response getAllAcounts() {
		return accountBusiness.getAllAccounts();
	}

	@GET
	@Path("/{accountId}")
	public Response getAccountByAccountId(@PathParam("accountId") String accountId) {
		return accountBusiness.getAccountByAccountId(accountId);
	}

	@POST
	public Response addAccount(AccountDto accountDto) {
		return accountBusiness.addAccount(accountDto);
	}

	@PUT
	public Response updateAccount(AccountDto accountDto) {
		return accountBusiness.updateAccount(accountDto);
	}

	@DELETE
	@Path("/{accountId}")
	public Response deleteAccount(@PathParam("accountId") String accountId) {
		return accountBusiness.deleteAccount(accountId);
	}

}
