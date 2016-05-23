package org.tappers.contact;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Contains contact details and necessary methods
 *
 * Created by Zack on 13/12/2015.
 */
public class Contact
{

    /**
     * The name of the contact
     */
    private String name;

    /**
     * The date the contact was created
     */
    private String date;

    /**
     * The character type
     */
    private String characterType;

    /**
     * The background colour
     */
    private String backgroundColour;

    /**
     * A list of all the transactions made by the contact
     */
    private List<Transaction> transactions;

    /**
     * Contact constructor
     *
     * @param name - the contact name
     * @param date - the contact date
     * @param characterType - the characterType
     * @param backgroundColour - the background colour
     * @param transactions - a list of transactions
     */
    public Contact(String name, String date, String characterType,
                   String backgroundColour, List<Transaction> transactions)
    {
        this.setName(name);
        this.setDate(date);
        this.setBackgroundColour(backgroundColour);
        this.setCharacterType(characterType);
        this.setTransactions(transactions);
    }

    /**
     * Contact constructor
     *
     * @param name - the contact name
     * @param date - the contact date
     * @param characterType - the characterType
     * @param backgroundColour - the background colour
     */
    public Contact(String name, String date, String characterType, String backgroundColour)
    {
        this(name, date, characterType, backgroundColour, null);
    }

    /**
     * Adds a transaction to the transactions list
     *
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
     *
     * @return - the total string
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

    /**
     * Getter for name
     *
     * @return - name
     */
    public String getName()
    {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name - the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Getter for date
     *
     * @return - date
     */
    public String getDate()
    {
        return date;
    }

    /**
     * Setter for date
     *
     * @param date - the new date
     */
    public void setDate(String date)
    {
        this.date = date;
    }

    /**
     * Getter for characterType
     *
     * @return - characterType
     */
    public String getCharacterType()
    {
        return characterType;
    }

    /**
     * Setter for characterType
     *
     * @param characterType - the new characterType
     */
    public void setCharacterType(String characterType)
    {
        this.characterType = characterType;
    }

    /**
     * Getter for backgroundColour
     *
     * @return - backgroundColour
     */
    public String getBackgroundColour()
    {
        return backgroundColour;
    }

    /**
     * Setter for backgroundColour
     *
     * @param backgroundColour - the new backgroundColour
     */
    public void setBackgroundColour(String backgroundColour)
    {
        this.backgroundColour = backgroundColour;
    }

    /**
     * Getter for transactions
     *
     * @return - transactions
     */
    public List<Transaction> getTransactions()
    {
        return transactions;
    }

    /**
     * Setter for transactions
     *
     * @param transactions - the new transactions
     */
    public void setTransactions(List<Transaction> transactions)
    {
        this.transactions = transactions;
    }
}
