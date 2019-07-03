package com.moneytransfer.controller;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.moneytransfer.business.MoneyTransferBusiness;
import com.moneytransfer.dto.MoneyTranferDto;

@Path("/transfer")
public class MoneyTransferController {
	
	private MoneyTransferBusiness moneyTransferBusiness = MoneyTransferBusiness.getInstance();

	@POST
	public Response transfer(MoneyTranferDto dto) {
		return moneyTransferBusiness.moneyTransfer(dto);
	}
}
