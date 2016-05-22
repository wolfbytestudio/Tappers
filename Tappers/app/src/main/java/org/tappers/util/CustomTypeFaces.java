package org.tappers.util;

import android.graphics.Typeface;

import org.tappers.MainActivity;

import java.util.HashMap;

/**
 * Created by Zack on 22/05/2016.
 */
public final class CustomTypeFaces
{

    /**
     * initializes the typefaces
     * @param activity - the activity to initialize with
     */
    public static void initialize(MainActivity activity)
    {
        Typeface thin = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Thin.ttf");
        Typeface light = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Light.ttf");
        Typeface regular = Typeface.createFromAsset(activity.getAssets(), "fonts/Roboto-Regular.ttf");

        typeFaces.put("thin", thin);
        typeFaces.put("light", light);
        typeFaces.put("regular", regular);
    }

    /**
     * The types of font
     */
    private static HashMap<String, Typeface> typeFaces = new HashMap<>();

    /**
     * Gets a font face
     * @param name - the name of the font
     * @return - the Typeface type
     */
    public static Typeface get(String name)
    {
        return typeFaces.get(name);
    }

}
