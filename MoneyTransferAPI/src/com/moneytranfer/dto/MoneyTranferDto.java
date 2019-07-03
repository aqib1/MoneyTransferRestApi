package com.moneytranfer.dto;

import java.time.LocalDate;
import java.util.UUID;

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
public class MoneyTranferDto {
	
	private int id;
	private String transactionId = UUID.randomUUID().toString();
	@NotNull
	private AccountDto toAccount;
	@NotNull
	private AccountDto fromAccount;
	private String transactionDate = LocalDate.now().toString();
}
