package com.example.zack.tapperstesting.util;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.zack.tapperstesting.contact.Contact;
import com.example.zack.tapperstesting.transaction.Transaction;
import com.example.zack.tapperstesting.transaction.TransactionType;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Zack on 16/12/2015.
 */
public class Loader
{

    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts()
    {
        return contacts;
    }

    public String loaderString = "";

    public void load()
    {
        loaderString = "1";

        ArrayList<Transaction> tempTrans;
        Contact currentContact;
        StringBuilder builder = new StringBuilder();
        loaderString = "2";

        String collected;

        contacts = new ArrayList<>();

        String fileName = "tappers.txt";
        File file = new File(fileName);

        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch (Exception io) { }
        }

        try {
            InputStream load = null;

            try {//Attempt to initialize input stream
                load = new BufferedInputStream(new FileInputStream(file));
                load.close();
            } catch(Exception io) {
                Log.d("a", "FAILED TO LOAD LOADER");
            }

            byte[] dataArray = new byte[load.available()];
            while (load.read(dataArray) != -1 )
            {
                collected = new String(dataArray);
            }
            load.close();

        } catch (Exception e) {
            //loaderString = "failed";
        }

        collected =  builder.toString();


        try
        {
            String[] sectors = collected.split(";");
            for(int i = 0; i < sectors.length - 1; i++)
            {
                tempTrans = new ArrayList<>();
                String[] segments = sectors[i].split(":");
                String name = segments[0];
                String total = segments[1];
                String date = segments[2];

                String[] transactionSegments = segments[3].split("-");

                for(int x = 0; x < transactionSegments.length; x++)
                {
                    String reason = transactionSegments[0];
                    double amount = Double.parseDouble(transactionSegments[1]);
                    String dateTran = transactionSegments[2];
                    TransactionType type =
                            TransactionType.valueOf(transactionSegments[3].toUpperCase());


                    Transaction trans = new Transaction(type, amount, dateTran, reason);
                    tempTrans.add(trans);

                }

                currentContact = new Contact(name, total, date, tempTrans);

                contacts.add(currentContact);
            }
        }
        catch(Exception io)
        {

        }

    }

}
