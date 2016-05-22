package org.tappers.contact;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zack on 13/12/2015.
 */
public class Contact
{

    private String name;

    private String date;

    private String characterType;

    private String backgroundColour;

    private List<Transaction> transactions;

    public Contact(String name, String date, String characterType,
                   String backgroundColour, List<Transaction> transactions)
    {
        this.setName(name);
        this.setDate(date);
        this.setBackgroundColour(backgroundColour);
        this.setCharacterType(characterType);
        this.setTransactions(transactions);
    }

    public Contact(String name, String date, String characterType,
                   String backgroundColour)
    {
        this.setName(name);
        this.setDate(date);
        this.setCharacterType(characterType);
        this.setBackgroundColour(backgroundColour);
        this.setTransactions(null);
    }

    /**
     * Adds a transaction to the transactions list
     * @param transaction - the transaction being added in
     */
    public void addTransaction(Transaction transaction)
    {
        if (getTransactions() == null)
            setTransactions((List)new ArrayList<>());

        getTransactions().add(0, transaction);
    }


    /**
     * Gets all transactions, calculates them all and totals up
     * then sets the total string to what the total transaction value is
     */
    public String getTotalString()
    {
        double value = 0D;

        for(Transaction t : getTransactions())
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
            return "You and " + getName() + " don't owe each other anything!";
        }
        else if(value < 0)
        {
            return "You owe " + getName() + " a total of " + formatter.format(Math.abs(value));
        }
        else if(value > 0)
        {
            return getName() + " owes you a total of " + formatter.format(Math.abs(value));
        }
        return "error: 105";
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDate()
    {
        return date;
    }

    public void setDate(String date)
    {
        this.date = date;
    }

    public String getCharacterType()
    {
        return characterType;
    }

    public void setCharacterType(String characterType)
    {
        this.characterType = characterType;
    }

    public String getBackgroundColour()
    {
        return backgroundColour;
    }

    public void setBackgroundColour(String backgroundColour)
    {
        this.backgroundColour = backgroundColour;
    }

    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}
