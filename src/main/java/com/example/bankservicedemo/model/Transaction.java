package com.example.bankservicedemo.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Min;
import lombok.Data;

@Entity(name = "transactions")
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "account_from")
    private Account accountFrom;
    @OneToOne
    @JoinColumn(name = "account_to")
    private Account accountTo;
    @Min(0)
    private double amount;
    private LocalDateTime date;
    private Type type;

    public enum Type {
        INCOMING, OUTCOMING
    }
}
