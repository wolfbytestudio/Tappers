package com.example.zack.tapperstesting.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.zack.tapperstesting.contact.Contact;
import com.example.zack.tapperstesting.transaction.Transaction;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Zack on 16/12/2015.
 */
public class Saver {

    private ArrayList<Contact> contacts;

    private String saveString;

    public String getSaveString() { return saveString; }

    private Context context;

    public Saver(ArrayList<Contact> contacts, Context context)
    {
        this.contacts = contacts;
        this.context = context;
    }

    public void save() {
        generateSaveString();
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput("tappers.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(saveString);
            outputStreamWriter.close();
        }catch(IOException e){ }
    }

    private void generateSaveString()
    {
        String temp = "";
        for (Contact contact : contacts) {
            temp += contact.name + ":" + contact.total + ":"
                    + contact.date + ":";
            if(contact.transactions.size() == 0)
            {
                temp += ";";
                saveString = temp;
                return;
            }

            for (Transaction transaction :
                    contact.transactions) {
                temp+= transaction.getReason() + "%"
                        + transaction.getAmount() + "%"
                        + transaction.getDate() + "%"
                        + transaction.getType() + "-";
            }
            temp += ";";
        }


        saveString = temp;

        Log.d("abc", saveString);
    }
}
