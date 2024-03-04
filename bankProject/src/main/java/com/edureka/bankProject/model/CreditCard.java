package com.edureka.bankProject.model;

import jakarta.persistence.*;
import lombok.Setter;

@Entity
@Table(name = "credit_card")
@Setter
public class CreditCard {

    private long credId;
    private String name;
    private int cardNo;
    private int cvv;
    private long amt;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long getCredId() {
        return credId;
    }

    @Column(name = "card_name" , nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "card_no" , nullable = false)
    public int getCardNo() {
        return cardNo;
    }
    @Column(nullable = false)
    public int getCvv() {
        return cvv;
    }

    @Column(nullable = false)
    public long getAmt() {
        return amt;
    }
}
