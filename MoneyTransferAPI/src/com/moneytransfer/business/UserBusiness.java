package com.moneytransfer.business;

import java.util.Objects;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.moneytransfer.dto.UserDto;
import com.moneytransfer.services.userserice.UserServiceImpl;
import com.moneytransfer.utility.TransactException;

public class UserBusiness {

	private static volatile UserBusiness userBusiness = null;

	private UserBusiness() {

	}

	public Response getAllUsers() {
		try {
			return Response.status(Status.OK).entity(UserServiceImpl.getInstance().getAllUsers())
					.type(MediaType.APPLICATION_JSON).build();

		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response getUserByUserId(String userId) {
		if (Objects.isNull(userId) || userId.isEmpty())
			return Response.status(Status.EXPECTATION_FAILED)
					.entity(new IllegalArgumentException("user-id cannot be null or empty")).build();
		try {
			return Response.status(Status.OK).entity(UserServiceImpl.getInstance().getUserByUserId(userId))
					.type(MediaType.APPLICATION_JSON).build();
		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response updateUser(UserDto userDto) {
		try {
			if(Objects.isNull(userDto.getUserId()) || userDto.getUserId().isEmpty())
				return Response.status(Status.EXPECTATION_FAILED).entity(new IllegalArgumentException("User-id required to update specific user.")).build();
			UserServiceImpl.getInstance().updateUser(userDto);
			return Response.status(Status.OK).entity(userDto).type(MediaType.APPLICATION_JSON).build();
		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response deleteUser(String userId) {
		try {
			UserServiceImpl.getInstance().deleteUser(userId);
			return Response.status(Status.OK).entity("User with id : "+userId+ " successfully deleted").type(MediaType.APPLICATION_JSON).build();
		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public Response createUser(UserDto userDto) {
		try {
			UserServiceImpl.getInstance().createUser(userDto);
			return Response.status(Status.OK).entity(userDto).type(MediaType.APPLICATION_JSON).build();
		} catch (TransactException e) {
			return Response.status(Status.EXPECTATION_FAILED).entity(e).build();
		}
	}

	public static UserBusiness getInstance() {
		if (userBusiness == null) {
			synchronized (UserBusiness.class) {
				if (userBusiness == null) {
					userBusiness = new UserBusiness();
				}
			}
		}
		return userBusiness;
	}
}
