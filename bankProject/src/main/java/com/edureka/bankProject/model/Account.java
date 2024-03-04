package com.edureka.bankProject.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "account_details")
@NoArgsConstructor
@Setter
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accId;
    private String name;
    private LocalDateTime dob;
    private String Address;
    private String email;

//    @OneToMany(cascade = CascadeType.ALL)
//    @Column(name = "trans_id")
//    private Transaction transaction;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
//    private List<Transaction> transactions;
    private Long balance;



    public long getAccId(){
        return accId;
    }

    @Column(name = "acc_name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "dob", nullable = false)
    public LocalDateTime getDob() {
        return dob;
    }

    @Column(name = "address", nullable = false)
    public String getAddress() {
        return Address;
    }

    @Column(name = "email", nullable = false)
    public String getEmail() {
        return email;
    }


//    public Transaction getTransaction() {
//        return transaction;
//    }
//    public List<Transaction> getTransactions() {
//        return transactions;
//    }
    public Long getBalance() {
        return balance;
    }
}