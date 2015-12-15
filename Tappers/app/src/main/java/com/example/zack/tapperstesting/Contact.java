package com.example.zack.tapperstesting;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zack on 13/12/2015.
 */
@SuppressWarnings("serial")
public class Contact implements Serializable
{

    public String name;
    public String total;
    public String date;
    public List<Transaction> transactions;

    public Contact(String name, String total, String date, List<Transaction> transactions)
    {
        this.name = name;
        this.total = total;
        this.date = date;
        this.transactions = transactions;
    }

    public Contact(String name, String total, String date)
    {
        this.name = name;
        this.total = total;
        this.date = date;
        this.transactions = null;
    }
    /**
     * Adds a transaction to the transactions list
     * @param transaction - the transaction being added in
     */
    public void addTransaction(Transaction transaction)
    {
        if (transactions == null)
            transactions = new ArrayList<>();

        transactions.add(0, transaction);
    }


    /**
     * Gets all transactions, calculates them all and totals up
     * then sets the total string to what the total transaction value is
     */
    public void setTotalString()
    {
        double value = 0D;

        for(Transaction t : transactions)
        {
            if(t.getType() == TransactionType.FROM)
            {
                value -= t.getAmount();
            }
            else
            {
                value += t.getAmount();
            }

        }

        NumberFormat formatter = NumberFormat.getCurrencyInstance();

        if(value == 0)
        {

            total = "You and " + name + " don't owe each other anything!";
            return;
        }

        if(value < 0)
        {
            total = "You owe " + name + " a total of " + formatter.format(Math.abs(value));
            return;
        }

        if(value > 0)
        {
            total = name + " owes you a total of " + formatter.format(Math.abs(value));
        }

    }

}
