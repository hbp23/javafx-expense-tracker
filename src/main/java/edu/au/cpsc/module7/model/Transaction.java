package edu.au.cpsc.module7.model;

import java.io.Serializable;
import java.time.LocalDate;

public class Transaction implements Serializable {

    // fields
    private LocalDate transactionDate;
    private String transactionCategory;
    private Double transactionAmount;
    private String transactionComment;

    // constructors
    public Transaction(LocalDate transactionDate, String transactionCategory, Double transactionAmount, String transactionComment) {
        this.transactionDate = transactionDate;
        this.transactionCategory = transactionCategory;
        this.transactionAmount = transactionAmount;
        this.transactionComment = transactionComment;
    }

    public Transaction() {}

    // methods
    @Override
    public String toString() {
        return "Transaction{" +
                "transactionDate=" + transactionDate +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", transactionAmount=" + transactionAmount +
                '}';
    }

    // getters and setters
    public LocalDate getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDate transactionDate) {
        if (transactionDate == null) { throw new IllegalArgumentException("transactionDate cannot be null"); }
        this.transactionDate = transactionDate;
    }

    public String getTransactionCategory() { return transactionCategory; }
    public void setTransactionCategory(String transactionCategory) {
        if (transactionCategory == null) { throw new IllegalArgumentException("transactionCategory cannot be null"); }
        this.transactionCategory = transactionCategory;
    }

    public Double getTransactionAmount() { return transactionAmount; }
    public void setTransactionAmount(Double transactionAmount) {
        if (transactionAmount == null) { throw new IllegalArgumentException("transactionAmount cannot be null"); }
        this.transactionAmount = transactionAmount;
    }

    public String getTransactionComment() { return transactionComment; }
    public void setTransactionComment(String transactionComment) {
        if (transactionComment == null) { throw new IllegalArgumentException("transactionComment cannot be null"); }
        this.transactionComment = transactionComment;
    }
}
