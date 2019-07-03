package com.moneytransfer.controller;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.moneytranfer.dto.UserDto;
import com.moneytransfer.business.UserBusiness;

@Path("/users")
public class UserController {

	private UserBusiness userBusiness = UserBusiness.getInstance();
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllUsers(){
		return userBusiness.getAllUsers();
	}
	@GET
	@Path("/{userId}")
	public Response getUserByUserId(@PathParam("userId") String userId) {
		return userBusiness.getUserByUserId(userId);
	}

	@POST
	public Response addUser(UserDto userDto) {
		return userBusiness.createUser(userDto);
	}

	@PUT
	public Response updateUser(UserDto userDto) {
		return userBusiness.updateUser(userDto);
	}

	@DELETE
	@Path("/{userId}")
	public Response deleteUser(@PathParam("userId") String userId) {
		return userBusiness.deleteUser(userId);
	}

}
