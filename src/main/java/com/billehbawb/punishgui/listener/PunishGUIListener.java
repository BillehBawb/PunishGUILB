package com.billehbawb.punishgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIClick implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (event.getClickedInventory().getName() == null || event.getClickedInventory() == null){
            return;
        }

        if (!event.getClickedInventory().getName().equalsIgnoreCase("punishgui")){
            return;
        }

        if (OpenGUI.punishReason == null){
            event.getWhoClicked().sendMessage(ChatColor.AQUA + "Something went wrong! (ER01)");
            return;
        }

        if (OpenGUI.punishedPlayer == null && !OpenGUI.offline){
            event.getWhoClicked().sendMessage(ChatColor.AQUA + "Something went wrong! (ER02)");
            return;
        }

        event.setCancelled(true);
        event.getWhoClicked().closeInventory();

        for (GUIButton button : PunishGUI.buttons) {
            if (button.getSlot() == event.getSlot()){
                clickedButton = button;
                break;
            }
        }

        if (clickedButton == null){
            return;
        }

        if (!OpenGUI.offline){
            switch(clickedButton.getAction()) {
                case IPBAN:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "ipban -s " + OpenGUI.punishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.punishedPlayer.getName() + " has been IP banned for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                case BAN:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "ban -s " + OpenGUI.punishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.punishedPlayer.getName() + " has been banned for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                    break;
                case MUTE:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "mute -s " + OpenGUI.punishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.punishedPlayer.getName() + " has been muted for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                    break;
                case PARDON:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "pardon " + OpenGUI.punishedPlayer.getName());
                    Bukkit.dispatchCommand(event.getWhoClicked(), "unmute " + OpenGUI.punishedPlayer.getName());
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.punishedPlayer.getName() + " has been pardoned.");
                    break;
            }
        } else {
            switch(clickedButton.getAction()) {
                case IPBAN:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "ipban -s " + OpenGUI.offlinePunishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.offlinePunishedPlayer.getName() + " has been IP banned for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                case BAN:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "ban -s " + OpenGUI.offlinePunishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.offlinePunishedPlayer.getName() + " has been banned for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                    break;
                case MUTE:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "mute -s " + OpenGUI.offlinePunishedPlayer.getName() + " " + clickedButton.getTime() + "s " + OpenGUI.punishReason);
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.offlinePunishedPlayer.getName() + " has been muted for " + clickedButton.getTime() + " seconds for reason " + OpenGUI.punishReason);
                    break;
                case PARDON:
                    Bukkit.dispatchCommand(event.getWhoClicked(), "pardon " + OpenGUI.offlinePunishedPlayer.getName());
                    Bukkit.dispatchCommand(event.getWhoClicked(), "unmute " + OpenGUI.offlinePunishedPlayer.getName());
                    event.getWhoClicked().sendMessage(ChatColor.AQUA + OpenGUI.offlinePunishedPlayer.getName() + " has been pardoned.");
                    break;
            }
        }

        if (OpenGUI.offline && clickedButton.getItem().getType() == Material.REDSTONE_BLOCK){
            event.getWhoClicked().getInventory().addItem(new ItemStack(Material.REDSTONE_BLOCK, 1));
        }

        return;
    }
}
