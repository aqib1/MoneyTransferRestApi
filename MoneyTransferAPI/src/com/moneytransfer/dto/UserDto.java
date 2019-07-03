package com.moneytransfer.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDto {
	
	private int id;
	@NotNull
	private String userId;
	@NotNull
	private String name;
	@NotNull
	private String email;
	@NotNull
	private String CNIC; // National Identity number
	private String address;
	private String contactNumber;
	private String lastModifiedAt = LocalDate.now().toString();
	//we can add more as we want per requirement
}
