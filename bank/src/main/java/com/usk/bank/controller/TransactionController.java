package com.usk.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.usk.bank.dto.TransferRequest;
import com.usk.bank.model.Account;
import com.usk.bank.model.Transaction;
import com.usk.bank.service.TransactionService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestBody TransferRequest transferRequest) {
		return ResponseEntity.ok(transactionService.transferFund(transferRequest));
	}

	@GetMapping("/history/{accountNumber}")
	public ResponseEntity<List<Transaction>> getHistory(@RequestParam Long accountNumber) {
		List<Transaction> history = transactionService.findByFromAccountOrToAccount(accountNumber);
		return ResponseEntity.ok(history);
	}

	@GetMapping("/findAccount")
	public Account findAccount(@RequestParam Long accountNumber) {
		return transactionService.findById(accountNumber);
	}

	@GetMapping("/statement")
	public ResponseEntity<List<Transaction>> getMonthlyStatement(@RequestParam Long account, @RequestParam int month,
			@RequestParam int year) {

		List<Transaction> statement = transactionService.findMonthlyStatement(account, month, year);
		return ResponseEntity.ok(statement);
	}
}
