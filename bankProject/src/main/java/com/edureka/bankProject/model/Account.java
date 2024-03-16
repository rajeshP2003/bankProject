package com.edureka.bankProject.model;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

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

    private Long balance;


    public long getAccId() {
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

    public Long getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accId=" + accId +
                ", name='" + name + '\'' +
                ", dob=" + dob +
                ", Address='" + Address + '\'' +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
