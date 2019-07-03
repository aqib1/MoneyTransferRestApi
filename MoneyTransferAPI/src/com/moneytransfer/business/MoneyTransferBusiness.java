package com.moneytransfer.business;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.moneytranfer.dto.MoneyTranferDto;
import com.moneytransfer.services.amounttransferservice.MoneyTransferServiceImpl;
import com.moneytransfer.utility.TransactException;

public class MoneyTransferBusiness {

	private static volatile MoneyTransferBusiness moneyTransferBusiness = null;
	private MoneyTransferServiceImpl moneyTransferServiceImpl = MoneyTransferServiceImpl.getInstance();

	private MoneyTransferBusiness() {

	}

	public Response moneyTransfer(MoneyTranferDto moneyTranferDto) {
		try {
			moneyTransferServiceImpl.moneyTransfer(moneyTranferDto);
			return Response.status(Status.OK).entity("Money tranfer sccessfully").type(MediaType.APPLICATION_JSON)
					.build();
		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public static MoneyTransferBusiness getInstance() {
		if (moneyTransferBusiness == null) {
			synchronized (MoneyTransferBusiness.class) {
				if (moneyTransferBusiness == null) {
					moneyTransferBusiness = new MoneyTransferBusiness();
				}
			}
		}
		return moneyTransferBusiness;
	}
}
