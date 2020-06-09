package com.billehbawb.punishgui.menu;

import com.billehbawb.punishgui.PunishGUI;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class GUIButton {

    public ItemStack item;
    public int slot;
    public PunishGUI.Action action;
    public int time;

}
