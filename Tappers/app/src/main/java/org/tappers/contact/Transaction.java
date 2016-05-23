package org.tappers.contact;

/**
 * Contains transactions
 *
 * Created by Zack on 14/12/2015.
 */
public class Transaction
{

    /**
     * The type of transaction
     */
    private TransactionType type;

    /**
     * The amount transactioned
     */
    private double amount;

    /**
     * The date the transaction was made
     */
    private String date;

    /**
     * The reason for the transaction
     */
    private String reason;

    /**
     * Transaction constructor
     *
     * @param type - the type of transaction
     * @param amount - the amount transactioned
     * @param date - the date of transaction
     * @param reason - the reason for the transaction
     */
    public Transaction(TransactionType type, double amount, String date, String reason)
    {
        setType(type);
        setAmount(amount);
        setDate(date);
        setReason(reason);
    }

    /**
     * Transaction constructor
     *
     * @param type - the type of transaction
     * @param amount - the amount transactioned
     * @param date - the date of transaction
     */
    public Transaction(TransactionType type, double amount, String date)
    {
        this(type, amount, date, "Reason Unknown");
    }

    /**
     * Getter for type
     *
     * @return - type
     */
    public TransactionType getType()
    {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type - the new type
     */
    public void setType(TransactionType type)
    {
        this.type = type;
    }

    /**
     * Getter for amount
     *
     * @return - amount
     */
    public double getAmount()
    {
        return amount;
    }

    /**
     * Setter for amount
     *
     * @param amount - the new amount
     */
    public void setAmount(double amount)
    {
        this.amount = amount;
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
     * Getter for reason
     *
     * @return - reason
     */
    public String getReason()
    {
        return reason;
    }

    /**
     * Setter for reason
     *
     * @param reason - the new reason
     */
    public void setReason(String reason)
    {
        this.reason = reason;
    }


}
