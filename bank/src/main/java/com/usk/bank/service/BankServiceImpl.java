package com.usk.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usk.bank.dto.AccountResponse;
import com.usk.bank.model.Account;
import com.usk.bank.repository.AccountRepository;

@Service
public class BankServiceImpl implements BankService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public AccountResponse getAccountByNumber(Long accountNumber) {
		Account account = accountRepository.findByAccountNumber(accountNumber)
				.orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));

		AccountResponse response = new AccountResponse();
		response.setAccountNumber(account.getAccountNumber());
		response.setBalance(account.getBalance());
		return response;
	}

	@Override

	public void debitAmount(Long accountNumber, Long amount) {

		Account account = accountRepository.findByAccountNumber(accountNumber)

				.orElseThrow(() -> new RuntimeException("Account not found: " + accountNumber));

		if (account.getBalance() < amount) {

			throw new RuntimeException("Insufficient balance");

		}

		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);

	}

}
