package com.example.zack.tapperstesting.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.zack.tapperstesting.contact.CharacterType;
import com.example.zack.tapperstesting.contact.Contact;
import com.example.zack.tapperstesting.transaction.Transaction;
import com.example.zack.tapperstesting.transaction.TransactionType;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Zack on 16/12/2015.
 */
public class LoadHandler
{

    private ArrayList<Contact> contacts;

    public ArrayList<Contact> getContacts()
    {
        return contacts;
    }

    public String loaderString = "";

    private Context context;

    public LoadHandler(Context context)
    {
        this.context = context;
    }

    public void load()
    {
        contacts = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("tappers.txt");

            if(inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader((inputStream));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String temp;
                StringBuilder strBuilder = new StringBuilder();

                while( (temp = bufferedReader.readLine()) != null ) {
                    strBuilder.append(temp);
                }
                inputStream.close();
                loaderString = strBuilder.toString();
            }

        } catch(FileNotFoundException e) {
        } catch(IOException e) { }


        try
        {
            ArrayList<Transaction> tempTrans;
            Contact currentContact;

            String[] sectors = loaderString.split(";");
            Log.d("abc", "Loading String: " + loaderString);

            for(int i = 0; i < sectors.length; i++)
            {
                CharacterType charType = CharacterType.MALE;
                tempTrans = new ArrayList<>();
                String[] segments = sectors[i].split(":");
                String name = segments[0];
                String total = segments[1];
                String date = segments[2];

                String character = segments[3];

                String background = segments[4];

                String[] transactionSegments = segments[5].split("-");

                if((transactionSegments.length) == 0)
                {
                    currentContact = new Contact(name, total, date, charType, background);
                    contacts.add(currentContact);
                }
                else
                {

                    for(int x = 0; x < transactionSegments.length; x++)
                    {
                        String[] newSeg = transactionSegments[x].split("%");

                        String reason = newSeg[0];
                        double amount = Double.parseDouble(newSeg[1]);
                        String dateTran = newSeg[2];

                        TransactionType type =
                                TransactionType.valueOf(newSeg[3].toUpperCase());

                        Transaction trans = new Transaction(type, amount, dateTran, reason);
                        Log.d("abc", "added transaction: " + type  );
                        tempTrans.add(trans);
                    }

                    try {
                        charType = CharacterType.valueOf(character.toUpperCase());
                    } catch (Exception io) {}

                    currentContact = new Contact(name, total, date,charType, background, tempTrans);

                    Log.d("abc", "ADDED NEW CONTACT");
                    contacts.add(currentContact);
                }
            }
        } catch(Exception io) {

            Log.d("abc", "IROOO " + io.toString());
        }

    }

}
