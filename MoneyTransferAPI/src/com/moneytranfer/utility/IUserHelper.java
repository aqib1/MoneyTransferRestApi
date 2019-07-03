package com.moneytranfer.utility;

import java.time.LocalDate;
import java.util.Objects;

import com.moneytranfer.dto.UserDto;

public class IUserHelper {

	private IUserHelper() {

	}

	public static void updateUser(UserDto oldDto, UserDto newDto) {
		oldDto.setUserId(newDto.getUserId());
		oldDto.setName(newDto.getName());
		oldDto.setEmail(newDto.getEmail());
		oldDto.setCNIC(newDto.getCNIC());
		if (!Objects.isNull(newDto.getAddress()) && !newDto.getAddress().isEmpty()) {
			oldDto.setAddress(newDto.getAddress());
		}
		if (!Objects.isNull(newDto.getContactNumber()) && !newDto.getContactNumber().isEmpty()) {
			oldDto.setContactNumber(newDto.getContactNumber());
		}
		oldDto.setLastModifiedAt(LocalDate.now().toString());
	}
}
