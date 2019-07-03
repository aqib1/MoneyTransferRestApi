package com.moneytransfer.test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.moneytranfer.dto.UserDto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class UserControllerTest {

	@Before
	public void initBaseUrlForUsers() {
		RestAssured.baseURI = "http://localhost:8080/MoneyTransferAPI/users";
	}

	@Test
	public void getAllUsers() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/all");
		assertEquals(response.getStatusCode(), 200);
		List<UserDto> li = response.body().jsonPath().getList(".", UserDto.class);
		assertEquals(li.get(0).getUserId(), "alex23");
		assertEquals(li.get(0).getCNIC(), "12304-12544-78454-77");
		assertEquals(li.get(1).getUserId(), "john12");
		assertEquals(li.get(1).getCNIC(), "12224-11511-12454-77");
	}

	@Test
	public void getUserByUserId() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.GET, "/alex23");
		assertEquals(response.getStatusCode(), 200);
		UserDto user = response.body().as(UserDto.class);
		assertEquals(user.getUserId(), "alex23");
		assertEquals(user.getCNIC(), "12304-12544-78454-77");
	}
	
	@Test
	public void deleteUserByUserId() {
		RequestSpecification httpRequest = RestAssured.given();
		Response response = httpRequest.request(Method.DELETE, "/daniel");
		assertEquals(response.getStatusCode(), 200);
	}
	
	@Test
	public void addUser() {
		UserDto newUser = new UserDto();
		newUser.setId(4);
		newUser.setName("testuser1");
		newUser.setUserId("testuser1id");
		newUser.setAddress("test test");
		newUser.setCNIC("1234-111");
		newUser.setContactNumber("+32615871526");
		newUser.setEmail("test@gmail.com");
		newUser.setLastModifiedAt(LocalDate.now().toString());
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(newUser)
        .post()
        .then()
        .statusCode(200)
        .extract()
        .response();
	}
	
	
	@Test
	public void updateUser() {
		UserDto newUser = new UserDto();
		newUser.setId(4);
		newUser.setName("testuser1");
		newUser.setUserId("john12");
		newUser.setAddress("test test1");
		newUser.setCNIC("12224-11511-12454-77");
		newUser.setContactNumber("+32615871526");
		newUser.setEmail("test@gmail.com");
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(newUser)
        .put()
        .then()
        .statusCode(200)
        .extract()
        .response();
	}
	
	@Test
	public void adddDuplicateUser() {
		UserDto newUser = new UserDto();
		newUser.setId(4);
		newUser.setName("testuser1");
		newUser.setUserId("testuser1id");
		newUser.setAddress("test test");
		newUser.setCNIC("1234-111");
		newUser.setContactNumber("+32615871526");
		newUser.setEmail("test@gmail.com");
		newUser.setLastModifiedAt(LocalDate.now().toString());
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(newUser)
        .post()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
	
	@Test
	public void updateUserWithNullUserId() {
		UserDto newUser = new UserDto();
		newUser.setId(4);
		newUser.setName("testuser1");
		newUser.setAddress("test test1");
		newUser.setCNIC("12224-11511-12454-77");
		newUser.setContactNumber("+32615871526");
		newUser.setEmail("test@gmail.com");
		
		RequestSpecification httpRequest = RestAssured.given();
		httpRequest.contentType(ContentType.JSON)
        .body(newUser)
        .put()
        .then()
        .statusCode(417)
        .extract()
        .response();
	}
}
