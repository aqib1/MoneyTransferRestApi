package com.moneytransfer.test;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import com.moneytransfer.dto.AccountDto;
import com.moneytransfer.dto.MoneyTranferDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class MoneyTransferControllerTest {

	@Before
	public void initBaseUrlForUsers() {
		RestAssured.baseURI = "http://localhost:8080/MoneyTransferAPI/transfer";
	}
	
	@Test
	public void moneyTransfer() {
		AccountDto to = new AccountDto();
		to.setAccountId("ahhhh1h2h1");
		
		AccountDto from = new AccountDto();
		from.setAccountId("egh1h2h1");
		from.setBalance(500L);
		MoneyTranferDto transfer = new MoneyTranferDto();
		transfer.setId(1);
		transfer.setToAccount(to);
		transfer.setFromAccount(from);
		transfer.setTransactionDate(LocalDate.now().toString());
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.contentType(ContentType.JSON)
        .body(transfer)
        .post()
        .then()
        .statusCode(200)
        .extract()
        .response();
	}
	
	@Test
	public void moneyTransferNegVal() {
		AccountDto to = new AccountDto();
		to.setAccountId("ahhhh1h2h1");
		
		AccountDto from = new AccountDto();
		from.setAccountId("egh1h2h1");
		from.setBalance(-500L);
		MoneyTranferDto transfer = new MoneyTranferDto();
		transfer.setId(1);
		transfer.setToAccount(to);
		transfer.setFromAccount(from);
		transfer.setTransactionDate(LocalDate.now().toString());
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.contentType(ContentType.JSON)
        .body(transfer)
        .post()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
	
	@Test
	public void moneyTransferHighVal() {
		AccountDto to = new AccountDto();
		to.setAccountId("ahhhh1h2h1");
		
		AccountDto from = new AccountDto();
		from.setAccountId("egh1h2h1");
		from.setBalance(100000500L);
		MoneyTranferDto transfer = new MoneyTranferDto();
		transfer.setId(1);
		transfer.setToAccount(to);
		transfer.setFromAccount(from);
		transfer.setTransactionDate(LocalDate.now().toString());
		RequestSpecification httpRequest = RestAssured.given();
		
		httpRequest.contentType(ContentType.JSON)
        .body(transfer)
        .post()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
}
