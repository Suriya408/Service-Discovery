package com.usk.bank.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.usk.bank.dto.TransferRequest;
import com.usk.bank.model.Account;
import com.usk.bank.model.Transaction;
import com.usk.bank.repository.AccountRepository;
import com.usk.bank.repository.TransactionRepository;

@Service
public class TransactionServiceimpl implements TransactionService {

	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	@Transactional
	public String transferFund(TransferRequest transferRequest) {

		Transaction trans = new Transaction();

		Optional<Account> fromOpt = accountRepository.findById(transferRequest.getFromAccount());
		Optional<Account> toOpt = accountRepository.findById(transferRequest.getToAccount());

		if (!fromOpt.isPresent() || !toOpt.isPresent()) {
			throw new RuntimeException("Insufficient funds in source account");
		}

		Account fromAcc = fromOpt.get();
		Account toAcc = toOpt.get();

		if (fromAcc.getBalance() < transferRequest.getAmount()) {
			throw new RuntimeException("insufficient funds in source account");
		}

		fromAcc.setBalance(fromAcc.getBalance() - transferRequest.getAmount());
		toAcc.setBalance(toAcc.getBalance() + transferRequest.getAmount());

		accountRepository.save(fromAcc);
		accountRepository.save(toAcc);

		transferRequest.setTimestamp(LocalDateTime.now());
		transferRequest.setType("DEBIT");
		BeanUtils.copyProperties(transferRequest, trans);
		transactionRepository.save(trans);

		transferRequest.setTimestamp(LocalDateTime.now());
		transferRequest.setType("CREDIT");
		BeanUtils.copyProperties(transferRequest, trans);
		transactionRepository.save(trans);
		return "Transfer Sucessful";
	}

	@Override
	public List<Transaction> findByFromAccountOrToAccount(Long accountNumber) {
		List<Transaction> history = transactionRepository.findByFromAccountOrToAccount(accountNumber,accountNumber);
		return history;
	}

	@Override
	public List<Transaction> findMonthlyStatement(Long account, int month, int year) {
		List<Transaction> statement = transactionRepository.findMonthlyStatement(account, month, year);
		return statement;
	}

	@Override
	public Account findById(Long accountNumber) {
		Optional<Account> transaction = accountRepository.findByAccountNumber(accountNumber);
		return transaction.orElseThrow(() -> new RuntimeException("AccountNumber not found :" + accountNumber));
	}

}
