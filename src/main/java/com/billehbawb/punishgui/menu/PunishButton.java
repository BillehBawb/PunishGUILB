package com.billehbawb.punishgui.menu;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.inventory.ItemStack;

@AllArgsConstructor
@Getter
public class PunishButton {

    public final ItemStack item;
    public final int slot;
    public final PunishAction action;
    public final int time;

}
