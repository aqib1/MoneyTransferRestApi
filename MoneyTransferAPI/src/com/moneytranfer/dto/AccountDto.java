package com.moneytranfer.dto;

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
public class AccountDto {
	private int id;
	@NotNull
	private String accountId;
	@NotNull
	private String accountName;
	@NotNull
	private Long balance;
	@NotNull
	private String currencyCode;
	@NotNull
	private UserDto accountHolder;
	@NotNull
	private long annualInterestRate;
	private String lastModifiedAt = LocalDate.now().toString();
}
