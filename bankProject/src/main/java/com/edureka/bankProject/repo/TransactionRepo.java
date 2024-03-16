package com.edureka.bankProject.repo;

import com.edureka.bankProject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    List<Transaction> findByAccId(long accountId);

    List<Transaction> findByAccIdAndTransDateTimeBetween(long accountId, LocalDateTime fromDate, LocalDateTime toDate);

}
