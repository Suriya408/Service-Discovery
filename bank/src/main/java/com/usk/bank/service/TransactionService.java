package com.usk.bank.service;

import java.util.List;

import com.usk.bank.dto.TransferRequest;
import com.usk.bank.model.Account;
import com.usk.bank.model.Transaction;

public interface TransactionService {

	String transferFund(TransferRequest transferRequest);

	List<Transaction> findByFromAccountOrToAccount(Long accountNumber);

	List<Transaction> findMonthlyStatement(Long account, int month, int year);

	Account findById(Long accountNumber);

}
