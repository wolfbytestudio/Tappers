package org.tappers.contact;

import org.tappers.R;

/**
 * Created by Zack on 20/12/2015.
 */
public enum Character
{

    DEFAULT_MALE("default male", R.drawable.male),
    DEFAULT_FEMALE("default female", R.drawable.female),
    STONER_BOB("stoner bob", R.drawable.stoner_bob),
    STONER_BOB_ALT("stoner bob alt", R.drawable.stoner_bob_alt),
    GOTH_GIRL("goth girl", R.drawable.goth_girl),
    ABIBA("abiba", R.drawable.abiba),
    GAMER_BOB("gamer bob", R.drawable.gamer_bob),
    ABUDADY("abudady", R.drawable.abudady);

    private String identifier;
    private int characterFile;

    Character(String identifier, int characterFile)
    {
        this.setIdentifier(identifier);
        this.setCharacterFile(characterFile);
    }


    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getCharacterFile() {
        return characterFile;
    }

    public void setCharacterFile(int characterFile) {
        this.characterFile = characterFile;
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
