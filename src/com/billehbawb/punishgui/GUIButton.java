package com.billehbawb.punishgui;

import org.bukkit.inventory.ItemStack;

public class GUIButton {

    public ItemStack item;
    public int slot;
    public String name;
    public PunishGUI.Action action;
    public int time;

    public ItemStack getItem(){
        return item;
    }

    public int getSlot(){
        return slot;
    }

    public String getName(){
        return name;
    }

    public PunishGUI.Action getAction(){
        return action;
    }

    public int getTime(){
        return time;
    }
}
