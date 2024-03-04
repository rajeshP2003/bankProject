package com.edureka.bankProject.repo;

import com.edureka.bankProject.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction,Long> {

    List<Transaction> findByAccId(long accountId);


}
