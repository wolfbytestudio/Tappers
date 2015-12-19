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
public class SaveHandler {

    private ArrayList<Contact> contacts;

    private String saveString;

    public String getSaveString() { return saveString; }

    private Context context;

    public SaveHandler(ArrayList<Contact> contacts, Context context)
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
            temp += contact.name + ":" + contact.total + ":" +
                     contact.date + ":" + contact.characterType.toString() + ":";

            int counter = 0;

            for (Transaction transaction :
                    contact.transactions) {
                counter++;
                temp+= transaction.getReason() + "%"
                        + transaction.getAmount() + "%"
                        + transaction.getDate() + "%"
                        + transaction.getType();
                if(counter != contact.transactions.size())
                {
                    temp += "-";
                }

            }
            temp += ";";
        }


        saveString = temp;

        Log.d("abc", saveString);
    }
}
