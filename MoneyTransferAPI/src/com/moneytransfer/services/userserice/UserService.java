package com.moneytransfer.services.userserice;

import java.util.List;

import com.moneytranfer.dto.UserDto;
import com.moneytransfer.utility.TransactException;

public interface UserService {

	List<UserDto> getAllUsers() throws TransactException;

	UserDto getUserByUserId(String userId) throws TransactException;

	void createUser(UserDto newUser) throws TransactException;

	void updateUser(UserDto updatedUser) throws TransactException;

	void deleteUser(String deleteUser) throws TransactException;
}
