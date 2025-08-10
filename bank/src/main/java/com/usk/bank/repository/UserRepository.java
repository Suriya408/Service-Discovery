package com.usk.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.usk.bank.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

	@Query(value = "SELECT NEXTVAL('acc_no_sequence')", nativeQuery = true)
	Long getAccountNumber();

	Users findByNameAndPassword(String userName,String password);
}
