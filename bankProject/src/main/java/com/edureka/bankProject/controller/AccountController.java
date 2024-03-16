package com.edureka.bankProject.controller;

import com.edureka.bankProject.config.Message;
import com.edureka.bankProject.model.Account;
import com.edureka.bankProject.model.CreditCard;
import com.edureka.bankProject.model.Transaction;
import com.edureka.bankProject.repo.AccountRepo;
import com.edureka.bankProject.repo.CreditCardRepo;
import com.edureka.bankProject.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@SessionAttributes("accountIdFromPath")
public class AccountController {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private CreditCardRepo creditCardRepo;

    @Autowired
    private AccountService accountService;

    @GetMapping("/accounts/create-account")
    public String createAccount(Model model) {
        return "create-account";
    }


    @PostMapping("/accounts/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody Account account, Model model) {

        Account savedAccount = accountRepo.save(account);
        long accountIdFromPath = savedAccount.getAccId();
        model.addAttribute("accountIdFromPath", accountIdFromPath);
        System.out.println(savedAccount.toString());
        System.out.println("accountIdFromPath : " + accountIdFromPath);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAccount);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Optional<Account>> getAccountDetails(@PathVariable long accountId) {

        Optional<Account> acc = accountRepo.findById(accountId);
        if (acc.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(acc);
        }
        throw new IllegalArgumentException("Account not found");
    }

    @GetMapping("/transactions")
    public String performTransactions() {
        return "transactions";
    }

    @PostMapping("/{accountIdFromPath}/debitFrom")
    public ResponseEntity<Map<String, Object>> debitFrom(@PathVariable long accountIdFromPath, @RequestBody Map<String, Object> account) {
        Optional<Account> ourAcc = accountRepo.findById(accountIdFromPath);
        System.out.println("accountIdFromPath : " + accountIdFromPath);

        if (ourAcc.isPresent()) {

            Long idTwo = new Long(account.get("accountid").toString()); // check for better way
            Long amt = new Long(account.get("amount").toString());  // new Long() is deprecated

            accountService.withdraw(idTwo, amt);
            accountService.deposit(accountIdFromPath, amt);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(account);

    }

    @PostMapping("/{accountIdFromPath}/creditTo")
    public ResponseEntity<Map<String, Object>> creditTo(@PathVariable long accountIdFromPath, @RequestBody Map<String, Object> account) {
        Optional<Account> ourAcc = accountRepo.findById(accountIdFromPath);
        if (ourAcc.isPresent()) {

            Long idTwo = new Long(account.get("accountid").toString()); // check for better way
            Long amt = new Long(account.get("amount").toString());  // new Long() is deprecated

            accountService.withdraw(accountIdFromPath, amt);
            accountService.deposit(idTwo, amt);
        } else {
            throw new IllegalArgumentException("Account not found");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(account);

    }

    @GetMapping("/displayStatement")
    public String displayStatementDateRange() {
        return "display-statement";
    }

    @PostMapping("/{accountIdFromPath}/displayStatement")
    public ResponseEntity<List<Transaction>> displayStatementDateRange(@PathVariable long accountIdFromPath, @RequestBody Map<String, Object> dateRange) {
        String fromDateStr = (String) dateRange.get("fromDate");
        String toDateStr = (String) dateRange.get("toDate");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        LocalDateTime fromDate = LocalDateTime.parse(fromDateStr, formatter);
        LocalDateTime toDate = LocalDateTime.parse(toDateStr, formatter);

        System.out.println("fromDateStr: " + fromDate);
        System.out.println("toDateStr: " + toDate);
        List<Transaction> transactions = accountService.getAllTransactionsBetweenDate(accountIdFromPath, fromDate, toDate);

        System.out.println(Arrays.toString(transactions.toArray()));
        return ResponseEntity.status(HttpStatus.OK).body(transactions);
    }

    @GetMapping("/authorize")
    public String authorizeCreditCard() {
        return "authorize";
    }

    @PostMapping("/authorize")
    public ResponseEntity<Void> authorizeCreditCard(@RequestBody CreditCard creditCard) {
        Message message = accountService.authCreditCard(creditCard, creditCard.getAmt());
        if (message.getResult().equalsIgnoreCase(Message.APPROVED.getResult())) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else if (message.getResult().equalsIgnoreCase(Message.REJECTED.getResult())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


}
