package com.edureka.bankProject.service;

import com.edureka.bankProject.config.Message;
import com.edureka.bankProject.config.TransactionType;
import com.edureka.bankProject.model.Account;
import com.edureka.bankProject.model.CreditCard;
import com.edureka.bankProject.model.Transaction;
import com.edureka.bankProject.repo.AccountRepo;
import com.edureka.bankProject.repo.CreditCardRepo;
import com.edureka.bankProject.repo.TransactionRepo;
import jakarta.transaction.Transactional;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private TransactionRepo transactionRepo;

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Transactional(rollbackOn = PropertyValueException.class)
    public void withdraw(Long accountId, Long amt) {
        Optional<Account> opAcc = accountRepo.findById(accountId);

        if (opAcc.isPresent()) {
            Account account = opAcc.get();
            Long newBalance = account.getBalance() - amt;
            if (newBalance.compareTo(0L) < 0) {
                throw new IllegalArgumentException("Insufficient Funds");
            }
            account.setBalance(newBalance);
            accountRepo.save(account);
            createTransaction(account, TransactionType.WITHDRAWAL, amt);

        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    @Transactional(rollbackOn = PropertyValueException.class)
    public void deposit(Long accountId, Long amt) {
        Optional<Account> opAcc = accountRepo.findById(accountId);

        if (opAcc.isPresent()) {
            Account account = opAcc.get();
            Long newBalance = account.getBalance() + amt;
            account.setBalance(newBalance);
            accountRepo.save(account);
            createTransaction(account, TransactionType.DEPOSIT, amt);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
    }

    public void createTransaction(Account account, TransactionType type, Long amt) {

        Transaction transaction = new Transaction();
        transaction.setAccId(account.getAccId());
        transaction.setDescription(type.getType());
        if (type.getType().equalsIgnoreCase("withdraw")) {
            transaction.setWithdrawAmt(amt);
        } else if (type.getType().equalsIgnoreCase("deposit")) {
            transaction.setDepositAmt(amt);
        }
        transaction.setTransDateTime(LocalDateTime.now());
        transaction.setAvailBal(account.getBalance());
        transactionRepo.save(transaction);


    }

    public List<Transaction> getAllTransactionsByAccountId(Long accountId) {
        return transactionRepo.findByAccId(accountId);
    }

    public List<Transaction> getAllTransactionsBetweenDate(long accountIdFromPath, LocalDateTime fromDate, LocalDateTime toDate) {
        return transactionRepo.findByAccIdAndTransDateTimeBetween(accountIdFromPath, fromDate, toDate);
    }

    @Transactional
    public Message authCreditCard(CreditCard creditCard, long amt) {
        String name = creditCard.getName();
        Optional<CreditCard> ourCreditCard = creditCardRepo.findByName(name);
        if (ourCreditCard.isPresent()) {
            if (creditCard.getCardNo() == ourCreditCard.get().getCardNo() && creditCard.getCvv() == ourCreditCard.get().getCvv()) {
                if (amt > 100000) {
                    return Message.REJECTED;
                } else {
                    return Message.APPROVED;
                }
            } else {
                return Message.INCORRECTCNO;
            }
        } else {
            return Message.NOTFOUND;
        }
    }
}
