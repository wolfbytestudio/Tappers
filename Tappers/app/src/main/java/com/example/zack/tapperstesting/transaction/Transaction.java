package com.example.zack.tapperstesting.transaction;

/**
 * Created by Zack on 14/12/2015.
 */
public class Transaction
{

    private TransactionType type;

    private double amount;

    private String date;

    private String reason;

    public Transaction(TransactionType type, double amount, String date, String reason)
    {
        this.type = type;
        this.amount = amount;
        this.date = date;
        this.reason = reason;
    }

    public TransactionType getType() {
        return type;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }


}
