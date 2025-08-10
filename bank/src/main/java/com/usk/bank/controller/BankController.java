package com.usk.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.bank.dto.AccountResponse;
import com.usk.bank.service.BankService;

@RestController
public class BankController {

	@Autowired
	private BankService bankService;

	@GetMapping("/account/{accountNumber}")
	public AccountResponse getAccount(@RequestParam Long accountNumber) {
		return bankService.getAccountByNumber(accountNumber);
	}

	@PostMapping("/debit")
	public String debitAmount(@RequestParam Long accountNumber, @RequestParam Long amount) {
		bankService.debitAmount(accountNumber, amount);
		return "Amount debited successfully.";
	}
}
