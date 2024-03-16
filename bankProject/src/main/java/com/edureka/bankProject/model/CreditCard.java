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

    @Column(name = "card_name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "card_no", nullable = false)
    public int getCardNo() {
        return cardNo;
    }

    @Column(nullable = false)
    public int getCvv() {
        return cvv;
    }

    public long getAmt() {
        return amt;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        CreditCard that = (CreditCard) o;

        if (credId != that.credId) return false;
        if (cardNo != that.cardNo) return false;
        if (cvv != that.cvv) return false;
        return name.equals(that.name);
    }
}
