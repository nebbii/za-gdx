package com.nebbii.zagdx;

import java.util.ArrayList;

public class SaveData {
    public String name;
    public int rubies;

    public Item equippedItem;
    public ArrayList<Treasure> treasures = new ArrayList<>();
    public ArrayList<Weapon> weapons = new ArrayList<>();
    public ArrayList<SavedLocationEntry> locations = new ArrayList<>();
    public ArrayList<Long> archipelagoChecks = new ArrayList<>();
    public String filename;
}
