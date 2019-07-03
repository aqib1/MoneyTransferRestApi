package com.moneytranfer.utility;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.moneytranfer.dto.AccountDto;
import com.moneytranfer.dto.UserDto;

public class DataUtils {
	private static volatile DataUtils dataUtils = null;
	private List<AccountDto> accountList = null;
	private List<UserDto> usersList = null;

	private DataUtils() {

	}

	public List<UserDto> getUsersList() {
		return usersList;
	}

	public List<AccountDto> getAccountList() {
		return accountList;
	}

	public void buildData() {
		synchronized (DataUtils.class) {
			usersList = new ArrayList<>();
			accountList = new ArrayList<>();

			// First User
			UserDto userDto = new UserDto();
			userDto.setId(1);
			userDto.setName("alex");
			userDto.setCNIC("12304-12544-78454-77");
			userDto.setEmail("alex@gmail.com");
			userDto.setUserId("alex23");
			userDto.setAddress("H#1, st#12, setinberg 117");
			userDto.setContactNumber("+3612718171771");
			usersList.add(userDto);

			// First account
			AccountDto accountDto = new AccountDto();
			accountDto.setId(1);
			accountDto.setAccountHolder(userDto);
			accountDto.setAccountId("ac123451");
			accountDto.setAccountName("Alex-AC-004");
			accountDto.setAnnualInterestRate(0l);
			accountDto.setLastModifiedAt(LocalDate.now().toString());
			accountDto.setCurrencyCode("USD");
			accountDto.setBalance(10000L);
			accountList.add(accountDto);

			// Second user
			userDto = new UserDto();
			userDto.setId(2);
			userDto.setName("john");
			userDto.setCNIC("12224-11511-12454-77");
			userDto.setEmail("john@gmail.com");
			userDto.setUserId("john12");
			userDto.setAddress("H#11, st#2, gutenberg 04");
			userDto.setContactNumber("+36155647885");
			usersList.add(userDto);

			// Second account
			accountDto = new AccountDto();
			accountDto.setId(2);
			accountDto.setAccountHolder(userDto);
			accountDto.setAccountId("jh222451");
			accountDto.setAccountName("JOHN-AC-124");
			accountDto.setAnnualInterestRate(0l);
			accountDto.setLastModifiedAt(LocalDate.now().toString());
			accountDto.setCurrencyCode("USD");
			accountDto.setBalance(20000L);
			accountList.add(accountDto);

			// third user
			userDto = new UserDto();
			userDto.setId(3);
			userDto.setName("daniel");
			userDto.setCNIC("05689-18944-78454-44");
			userDto.setEmail("d_aniw@gmail.com");
			userDto.setUserId("dan19");
			userDto.setAddress("H#1A, st#C-2, balaha 89");
			userDto.setContactNumber("+3688787777");
			usersList.add(userDto);

			// third account
			accountDto = new AccountDto();
			accountDto.setId(3);
			accountDto.setAccountHolder(userDto);
			accountDto.setAccountId("dyn123451");
			accountDto.setAccountName("Daniel-AC-004");
			accountDto.setAnnualInterestRate(0l);
			accountDto.setLastModifiedAt(LocalDate.now().toString());
			accountDto.setCurrencyCode("USD");
			accountDto.setBalance(5000L);
			
			accountList.add(accountDto);

			// fourth user
			userDto = new UserDto();
			userDto.setId(4);
			userDto.setName("sam");
			userDto.setCNIC("0561289-18944-78454-44");
			userDto.setEmail("sam_aniw@gmail.com");
			userDto.setUserId("sam232");
			userDto.setAddress("H#1A, st#C-2, balaha 89");
			userDto.setContactNumber("+3688787777");
			usersList.add(userDto);

			// forth account
			accountDto = new AccountDto();
			accountDto.setId(4);
			accountDto.setAccountHolder(userDto);
			accountDto.setAccountId("egh1h2h1");
			accountDto.setAccountName("sam-AC-004");
			accountDto.setAnnualInterestRate(0l);
			accountDto.setLastModifiedAt(LocalDate.now().toString());
			accountDto.setCurrencyCode("USD");
			accountDto.setBalance(50000L);

			accountList.add(accountDto);
			
			// fifth user
			userDto = new UserDto();
			userDto.setId(5);
			userDto.setName("eam");
			userDto.setCNIC("0561289-18944-78454-44");
			userDto.setEmail("eam_aniw@gmail.com");
			userDto.setUserId("eam232");
			userDto.setAddress("H#1A, st#C-2, balaha 89");
			userDto.setContactNumber("+3688787777");
			usersList.add(userDto);

			// fifth account
			accountDto = new AccountDto();
			accountDto.setId(5);
			accountDto.setAccountHolder(userDto);
			accountDto.setAccountId("ahhhh1h2h1");
			accountDto.setAccountName("sam-AC-004");
			accountDto.setAnnualInterestRate(0l);
			accountDto.setLastModifiedAt(LocalDate.now().toString());
			accountDto.setCurrencyCode("USD");
			accountDto.setBalance(200L);

			accountList.add(accountDto);
		}
	}

	public static DataUtils getInstance() {
		if (dataUtils == null) {
			synchronized (DataUtils.class) {
				if (dataUtils == null) {
					dataUtils = new DataUtils();
				}
			}
		}
		return dataUtils;
	}
}
