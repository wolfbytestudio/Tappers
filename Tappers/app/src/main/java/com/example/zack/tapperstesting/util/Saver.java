package com.example.zack.tapperstesting.util;

import android.app.Activity;
import android.content.Context;

import com.example.zack.tapperstesting.contact.Contact;
import com.example.zack.tapperstesting.transaction.Transaction;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Zack on 16/12/2015.
 */
public class Saver extends Activity {

    private ArrayList<Contact> contacts;

    private String saveString;

    public String getSaveString() { return saveString; }

    public Saver(ArrayList<Contact> contacts) {
        this.contacts = contacts;
    }

    public void save() {
        String filename = "tappers";
        generateSaveString();
        FileOutputStream outputStream;
        try {
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            outputStream.write(saveString.getBytes());
            outputStream.close();
        } catch (Exception io) {

        }
    }

    private void generateSaveString()
    {
        String temp = "";
        for (Contact contact : contacts) {

            temp += contact.name + ":" + contact.total + ":"
                    + contact.date + ":";

            for (Transaction transaction :
                    contact.transactions) {
                temp+= transaction.getReason() + "-"
                        + transaction.getAmount() + "-"
                        + transaction.getDate() + "-"
                        + transaction.getType();
            }
        }

        temp += ";";

        saveString = temp;
    }
}
