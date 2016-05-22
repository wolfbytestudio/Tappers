package org.tappers;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.tappers.contact.Contact;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


/**
 * This class contains all the contacts and necessary methods
 *
 * Created by Zack on 22/05/2016.
 */
public class Contacts
{

    /**
     * The classes singleton
     */
    public static final Contacts SINGLETON = new Contacts();


    /**
     * Private constructor to stop initializing this class
     */
    private Contacts() { }


    /**
     * A list of all the contacts
     */
    private List<Contact> contacts = new ArrayList<>();

    /**
     * Gets the contacts
     * @return - contacts
     */
    public List<Contact> getContacts()
    {
        return contacts;
    }

    /**
     * Sets the contacts
     * @param contacts - the new list of contacts
     */
    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
    }

    /**
     * Gets the position of the contact
     * @param name
     * @return
     */
    public int getContactPosition(String name)
    {
        int counter = 0;
        for(Contact con : contacts)
        {
            if(con.getName().equalsIgnoreCase(name))
            {
                return counter;
            }
            counter++;
        }
        return -1;
    }

    public static final Gson GSON = new Gson();

    private static final String FILE = "tappers.json";

    /**
     * Loads data from json
     * @param context - the contxt
     */
    public void load(Context context)
    {
        StringBuilder data = new StringBuilder();
        try
        {
            Log.d("abc", "Try");
            InputStream inputStream = context.openFileInput(FILE);
            if(inputStream != null)
            {
                InputStreamReader inputStreamReader = new InputStreamReader((inputStream));
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String temp;

                while( (temp = bufferedReader.readLine()) != null ) {
                    Log.d("abc", temp);
                    data.append(temp);
                }
                inputStream.close();
            }

        } catch (FileNotFoundException e) {Log.d("abc", "cant find file"); }
        catch (IOException e){ Log.d("abc", "exception"); }

        Log.d("abc", data.toString());

        loadFromJson(data.toString());

        if(contacts == null)
        {
            contacts = new ArrayList<>();
        }
    }

    /**
     * Saves all the contacts
     * @param context
     */
    public void save(Context context)
    {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(FILE, Context.MODE_PRIVATE));
            outputStreamWriter.write(GSON.toJson(contacts));
            outputStreamWriter.close();
        }catch(IOException e){ }
    }

    /**
     * Loads contacts based on a json string
     * @param json
     */
    public void loadFromJson(String json)
    {
        Type type = new TypeToken<List<Contact>>(){}.getType();
        contacts = GSON.fromJson(json, type);
    }
}
