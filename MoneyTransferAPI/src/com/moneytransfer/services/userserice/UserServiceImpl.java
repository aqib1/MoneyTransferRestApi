package com.moneytransfer.services.userserice;

import java.util.List;

import com.moneytranfer.dto.UserDto;
import com.moneytranfer.utility.DataUtils;
import com.moneytranfer.utility.IUserHelper;
import com.moneytranfer.utility.TransactException;

public class UserServiceImpl implements UserService {
	private static volatile UserServiceImpl userServiceImpl = null;

	// General - Exception handling in the case if you replace in memory data with
	// actual database
	private UserServiceImpl() {

	}

	@Override
	public List<UserDto> getAllUsers() throws TransactException {
		synchronized (UserServiceImpl.class) {
			try {
				return DataUtils.getInstance().getUsersList();
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while fetching users-list form database", e);
			}
		}
	}

	@Override
	public UserDto getUserByUserId(String userId) throws TransactException {
		synchronized (UserServiceImpl.class) {
			try {
				return DataUtils.getInstance().getUsersList().stream().filter(x -> x.getUserId().equals(userId))
						.findFirst().orElse(null);
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while fetching User by userId form database", e);
			}
		}
	}

	@Override
	public void createUser(UserDto newUser) throws TransactException {
		synchronized (UserServiceImpl.class) {
			try {
				if (DataUtils.getInstance().getUsersList().stream()
						.anyMatch(x -> x.getUserId().equals(newUser.getUserId()))) {
					throw new TransactException(
							"Data replication -> UserId : " + newUser.getUserId() + " already exists in database",
							null);
				}
				DataUtils.getInstance().getUsersList().add(newUser);
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while adding user in database", e);
			}
		}
	}

	@Override
	public void updateUser(UserDto updatedUser) throws TransactException {
		synchronized (UserServiceImpl.class) {
			try {
				DataUtils.getInstance().getUsersList().stream().forEach(x -> {
					if (x.getUserId().equals(updatedUser.getUserId())) {
						IUserHelper.updateUser(x, updatedUser);
					}
				});
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while updating user in database", e);
			}
		}
	}

	@Override
	public void deleteUser(String deleteUser) throws TransactException {
		synchronized (UserServiceImpl.class) {
			try {
				DataUtils.getInstance().getUsersList().removeIf(x -> x.getUserId().equals(deleteUser));
			} catch (Exception e) {
				throw new TransactException("General Exception occurs while deleting user in database", e);
			}
		}
	}

	public static UserServiceImpl getInstance() throws TransactException {
		if (userServiceImpl == null) {
			synchronized (UserServiceImpl.class) {
				if (userServiceImpl == null) {
					userServiceImpl = new UserServiceImpl();
				}
			}
		}
		return userServiceImpl;
	}
}
