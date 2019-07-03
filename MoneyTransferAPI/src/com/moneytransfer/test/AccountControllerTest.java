package com.moneytransfer.test;

import static org.junit.Assert.assertEquals;
import java.time.LocalDate;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

import com.moneytransfer.dto.AccountDto;
import com.moneytransfer.dto.UserDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AccountControllerTest {
	@Before
	public void initBaseUrlForUsers() {
		RestAssured.baseURI = "http://localhost:8080/MoneyTransferAPI/accounts";
	}

	@Test
	public void getAllAccounts() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/all");
		assertEquals(response.getStatusCode(), 200);
		List<AccountDto> li = response.body().jsonPath().getList(".", AccountDto.class);
		assertEquals(li.get(0).getAccountId(), "ac123451");
		assertEquals(li.get(0).getAccountHolder().getName(), "alex");
	}
	
	@Test
	public void getAccountByAccountId() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/ac123451");
		assertEquals(response.getStatusCode(), 200);
		AccountDto accountDto = response.body().as(AccountDto.class);
		assertEquals(accountDto.getAccountId(), "ac123451");
		assertEquals(accountDto.getAccountHolder().getName(), "alex");
	}
	
	@Test
	public void addAccount() {
		UserDto userDto = new UserDto();
		userDto.setId(5);
		userDto.setName("daniel1");
		userDto.setCNIC("05689-18944-78454-44");
		userDto.setEmail("d_aniw@gmail.com");
		userDto.setUserId("dan191");
		userDto.setAddress("H#1A, st#C-2, balaha 89");
		userDto.setContactNumber("+3688787777");
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(5);
		accountDto.setAccountHolder(userDto);
		accountDto.setAccountId("djsn123451");
		accountDto.setAccountName("Daniel-AC-004");
		accountDto.setAnnualInterestRate(0l);
		accountDto.setLastModifiedAt(LocalDate.now().toString());
		accountDto.setCurrencyCode("USD");
		accountDto.setBalance(5000L);
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(accountDto)
        .post()
        .then()
        .statusCode(200)
        .extract()
        .response();
	}
	
	@Test
	public void addDuplicateAccount() {
		UserDto userDto = new UserDto();
		userDto.setId(5);
		userDto.setName("daniel1");
		userDto.setCNIC("05689-18944-78454-44");
		userDto.setEmail("d_aniw@gmail.com");
		userDto.setUserId("dan191");
		userDto.setAddress("H#1A, st#C-2, balaha 89");
		userDto.setContactNumber("+3688787777");
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(5);
		accountDto.setAccountHolder(userDto);
		accountDto.setAccountId("djsn123451");
		accountDto.setAccountName("Daniel-AC-004");
		accountDto.setAnnualInterestRate(0l);
		accountDto.setLastModifiedAt(LocalDate.now().toString());
		accountDto.setCurrencyCode("USD");
		accountDto.setBalance(5000L);
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(accountDto)
        .post()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
	
	@Test
	public void updateAccount() {
		UserDto userDto = new UserDto();
		userDto.setId(5);
		userDto.setName("daniel1");
		userDto.setCNIC("05689-18944-78454-44");
		userDto.setEmail("d_aniw@gmail.com");
		userDto.setUserId("dan191");
		userDto.setAddress("H#1A, st#C-2, balaha 89");
		userDto.setContactNumber("+3688787777");
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(5);
		accountDto.setAccountHolder(userDto);
		accountDto.setAccountId("djsn123451");
		accountDto.setAccountName("Daniel-AC-0114");
		accountDto.setAnnualInterestRate(0l);
		accountDto.setLastModifiedAt(LocalDate.now().toString());
		accountDto.setCurrencyCode("USD");
		accountDto.setBalance(5000L);
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(accountDto)
        .put()
        .then()
        .statusCode(200)
        .extract()
        .response();
	}
	
	
	@Test
	public void updateAccountWithNullAccountId() {
		UserDto userDto = new UserDto();
		userDto.setId(5);
		userDto.setName("daniel1");
		userDto.setCNIC("05689-18944-78454-44");
		userDto.setEmail("d_aniw@gmail.com");
		userDto.setUserId("dan191");
		userDto.setAddress("H#1A, st#C-2, balaha 89");
		userDto.setContactNumber("+3688787777");
		
		AccountDto accountDto = new AccountDto();
		accountDto.setId(5);
		accountDto.setAccountHolder(userDto);
		accountDto.setAccountName("Daniel-AC-0114");
		accountDto.setAnnualInterestRate(0l);
		accountDto.setLastModifiedAt(LocalDate.now().toString());
		accountDto.setCurrencyCode("USD");
		accountDto.setBalance(5000L);
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(accountDto)
        .put()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
	
	@Test
	public void deleteAccountByAccountId() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.DELETE, "/djsn123451");
		assertEquals(response.getStatusCode(), 200);
	}
}
