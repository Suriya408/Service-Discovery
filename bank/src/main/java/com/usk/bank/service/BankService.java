package com.usk.bank.service;

import com.usk.bank.dto.AccountResponse;

public interface BankService {

	AccountResponse getAccountByNumber(Long accountNumber);

	void debitAmount(Long accountNumber, Long amount);

}
