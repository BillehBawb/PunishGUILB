package com.billehbawb.punishgui.listener;

import com.billehbawb.punishgui.PunishGUI;
import com.billehbawb.punishgui.menu.PunishButton;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PunishGUIListener implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent event) {
        if (!event.getInventory().getName().equalsIgnoreCase("§cPunishGUI")) {
            return;
        }

        if (PunishGUI.getInstance().getPunishManager().getPunishData(event.getWhoClicked().getUniqueId()) == null) {
            event.getWhoClicked().sendMessage("§cError loading Punish Data!");
            event.getWhoClicked().closeInventory();
            return;
        }

        event.setCancelled(true);

        PunishButton clickedButton = null;

        for (PunishButton button : PunishGUI.getInstance().getPunishManager().getButtons()) {
            if (button.getSlot() == event.getSlot()) {
                clickedButton = button;
                break;
            }
        }

        if (clickedButton == null) {
            return;
        }

        PunishGUI.getInstance().getPunishManager().punish((Player) event.getWhoClicked(), clickedButton.getAction(), clickedButton.getTime());
    }

}
