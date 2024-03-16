package com.edureka.bankProject.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@NoArgsConstructor
@Data
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trans_id")
    private long transId;

    private long accId;

    @Column(name = "trans_date_time", nullable = false)
    private LocalDateTime transDateTime;
    private String description;
    @Column(name = "cheq_no")
    private Long chequeNo;
    @Column(name = "with_amt")
    private Long withdrawAmt;
    @Column(name = "depo_amt")
    private Long depositAmt;
    @Column(name = "avail_bal", nullable = false)
    private Long availBal;

}
