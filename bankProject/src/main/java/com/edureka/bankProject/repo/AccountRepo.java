package com.edureka.bankProject.repo;

import com.edureka.bankProject.model.Account;
import com.edureka.bankProject.model.Transaction;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account,Long> {

//    @PersistenceContext
//    EntityManager entityManager = null;
//    default List<Transaction> findTransactionsByAccountId(Long accountId) {
//        return entityManager.createQuery(
//                        "SELECT t FROM Transaction t WHERE t.account.accId = :accountId",
//                        Transaction.class)
//                .setParameter("accountId", accountId)
//                .getResultList();
//    }
}
