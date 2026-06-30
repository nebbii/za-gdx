package com.nebbii.zagdx;

import java.util.ArrayList;

public class ActorJsonEntry {
    public String type;
    public float x;
    public float y;

    // extra fields
    public String __comment;
    public String pickupType;
    public String pickupItem;
    public String trigger;
    public boolean purchasable;
    public int price;

    // pathed enemies
    public String pathMode;
    public ArrayList<ActorPathPointJsonEntry> path = new ArrayList<>();

    // rubies
    public String rubyType;
}
