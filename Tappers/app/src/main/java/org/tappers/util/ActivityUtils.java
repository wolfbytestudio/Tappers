package org.tappers.util;

import org.tappers.contact.Contact;

import java.util.ArrayList;

/**
 * Created by Zack on 16/12/2015.
 */
public class ActivityUtils
{

    public static final int CONTACT = 0x02;

    public static final int CONTACT_RETURN = 0x03;

    public static final int NEW_CONTACT = 0x05;

    public static final int NEW_CONTACT_RETURN = 0x06;

    public static final Contact getContactByName(ArrayList<Contact> contacts, String name)
    {
        Contact contact = null;
        for (Contact con: contacts) {
            if(con.getName().equals(name))
            {
                return contact;
            }
        }
        return null;
    }


}
