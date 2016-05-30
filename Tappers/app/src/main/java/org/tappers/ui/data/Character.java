package org.tappers.ui.data;

import org.tappers.R;

/**
 * Character is an enum which contains all characters
 *
 * Created by Zack on 20/12/2015.
 */
public enum Character
{

    DEFAULT_MALE("default male", R.drawable.male, R.drawable.male_small),
    DEFAULT_FEMALE("default female", R.drawable.female, R.drawable.female_small),

    STONER_BOB("stoner bob", R.drawable.stoner_bob, R.drawable.stoner_bob_small),
    STONER_BOB_ALT("stoner bob alt", R.drawable.stoner_bob_alt, R.drawable.stoner_bob_alt_small),

    GOTH_GIRL("goth girl", R.drawable.goth_girl, R.drawable.goth_girl_small),

    ABIBA("abiba", R.drawable.abiba, R.drawable.abiba_small),
    GAMER_BOB("gamer bob", R.drawable.gamer_bob, R.drawable.gamer_bob_small),

    BLUE_DRESS_FEMALE("blue dress female", R.drawable.blue_dress_female, R.drawable.blue_dress_female_small),
    RED_DRESS_FEMALE("red dress female", R.drawable.red_dress_female, R.drawable.red_dress_female_small),

    ABUDADY("abudady", R.drawable.abudady, R.drawable.abudady_small);

    /**
     * The string identifier
     */
    private String identifier;

    /**
     * The character file
     */
    private int characterFile;

    /**
     * The character small file
     */
    private int characterFileSmall;

    /**
     * Constructor for Character
     */
    Character(String identifier, int characterFile, int characterFileSmall)
    {
        this.setIdentifier(identifier);
        this.setCharacterFile(characterFile);
        this.setCharacterSmallFile(characterFileSmall);
    }


    public String getIdentifier()
    {
        return identifier;
    }

    public void setIdentifier(String identifier)
    {
        this.identifier = identifier;
    }

    public int getCharacterFile()
    {
        return characterFile;
    }

    public void setCharacterFile(int characterFile)
    {
        this.characterFile = characterFile;
    }

    public int getCharacterSmallFile()
    {
        return characterFileSmall;
    }

    public void setCharacterSmallFile(int characterFile)
    {
        this.characterFileSmall = characterFile;
    }

    public static Character getCharacterForName(String name)
    {
        for(Character character : values())
        {
            if(character.identifier.equalsIgnoreCase(name))
            {
                return character;
            }
        }
        return null;
    }

}
