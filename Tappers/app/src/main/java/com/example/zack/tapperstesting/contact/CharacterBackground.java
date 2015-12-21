package com.example.zack.tapperstesting.contact;

import com.example.zack.tapperstesting.R;

/**
 * Created by Zack on 20/12/2015.
 */
public enum CharacterBackground {

    DEFAULT("default", R.drawable.bgcol_default, R.drawable.bgcol_default_big),
    BLUE("blue", R.drawable.bgcol_blue, R.drawable.bgcol_blue_big),
    GREEN("green", R.drawable.bgcol_green, R.drawable.bgcol_green_big),
    TURQ("turq", R.drawable.bgcol_turq, R.drawable.bgcol_turq_big),
    PURPLE("purple", R.drawable.bgcol_purple, R.drawable.bgcol_purple_big),
    PINK("pink", R.drawable.bgcol_pink, R.drawable.bgcol_pink_big),
    RED("red", R.drawable.bgcol_red, R.drawable.bgcol_red_big),
    ORANGE("orange", R.drawable.bgcol_orange, R.drawable.bgcol_orange_big),
    GOLD("gold", R.drawable.bgcol_gold, R.drawable.bgcol_gold_big),
    YELLOW("yellow", R.drawable.bgcol_yellow, R.drawable.bgcol_yellow_big),
    BLACK("black", R.drawable.bgcol_black, R.drawable.bgcol_black_big);


    private String identifier;
    private int smallBackground;
    private int largeBackground;

    private CharacterBackground(String identifier, int smallBackground, int largeBackground)
    {
        this.setIdentifier(identifier);
        this.setSmallBackground(smallBackground);
        this.setLargeBackground(largeBackground);
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    public int getSmallBackground() {
        return smallBackground;
    }

    public void setSmallBackground(int smallBackground) {
        this.smallBackground = smallBackground;
    }

    public int getLargeBackground() {
        return largeBackground;
    }

    public void setLargeBackground(int largeBackground) {
        this.largeBackground = largeBackground;
    }

    /**
     * Gets the background for the id!!!!!!
     * @param id
     * @return
     */
    public static CharacterBackground getBackgroundForId(String id)
    {
        for(CharacterBackground bg : values())
        {
            if(id.equalsIgnoreCase(bg.getIdentifier()))
            {
                return bg;
            }
        }
        return null;
    }
}
