package com.usk.bank.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.usk.bank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	List<Transaction> findByFromAccountOrToAccount(Long accountNumber, Long accountNumber2);

	@Query("SELECT t FROM Transaction t " + "WHERE (t.fromAccount = :account OR t.toAccount = :account) "
			+ "AND FUNCTION('MONTH', t.timestamp) = :month " + "AND FUNCTION('YEAR', t.timestamp) = :year")
	List<Transaction> findMonthlyStatement(@Param("account") Long account, @Param("month") int month,
			@Param("year") int year);

}
