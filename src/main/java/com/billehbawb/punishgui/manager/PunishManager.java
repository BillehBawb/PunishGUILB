package com.billehbawb.punishgui.manager;

import com.billehbawb.punishgui.PunishGUI;
import com.billehbawb.punishgui.command.PunishCommand;
import com.billehbawb.punishgui.manager.data.PunishData;
import com.billehbawb.punishgui.menu.PunishAction;
import com.billehbawb.punishgui.menu.PunishButton;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Getter
public class PunishManager {

    private final List<PunishButton> buttons;
    private final HashMap<UUID, PunishData> punishData;

    public PunishManager() {
        buttons = Lists.newArrayList();
        punishData = Maps.newHashMap();

        loadButtons();
    }

    private void loadButtons() {
        buttons.clear();

        for (String buttonID : PunishGUI.getInstance().getConfig().getStringList("Buttons")) {
            PunishButton button = buttonFromString(buttonID);
            buttons.add(button);
        }
    }

    public void punish(Player punisher, PunishAction action, int time) {
        PunishData data = getPunishData(punisher.getUniqueId());

        switch(action) {
            case IPBAN:
                Bukkit.dispatchCommand(punisher, "ipban -s " + data.getTarget().getName() + " " + time + "s " + data.getReason());
                punisher.sendMessage(ChatColor.AQUA + data.getTarget().getName() + " has been IP banned for " + time + " seconds for reason " + data.getReason());
            case BAN:
                Bukkit.dispatchCommand(punisher, "ban -s " + data.getTarget().getName() + " " + time + "s " + data.getReason());
                punisher.sendMessage(ChatColor.AQUA + data.getTarget().getName() + " has been banned for " + time + " seconds for reason " + data.getReason());
                break;
            case MUTE:
                Bukkit.dispatchCommand(punisher, "mute -s " + data.getTarget().getName() + " " + time + "s " + data.getReason());
                punisher.sendMessage(ChatColor.AQUA + data.getTarget().getName() + " has been muted for " + time + " seconds for reason " + data.getReason());
                break;
            case PARDON:
                Bukkit.dispatchCommand(punisher, "pardon " + data.getTarget().getName());
                Bukkit.dispatchCommand(punisher, "unmute " + data.getTarget().getName());
                punisher.sendMessage(ChatColor.AQUA + data.getTarget().getName() + " has been pardoned.");
                break;
        }

        getPunishData().remove(punisher.getUniqueId());
    }

    public void openGUI(Player player) {
        Inventory inventory = Bukkit.createInventory(null, 27, "§cPunishGUI");

        for (PunishButton button : buttons) {
            inventory.setItem(button.getSlot(), button.getItem());
        }

        player.openInventory(inventory);
    }

    public PunishData getPunishData(UUID uuid) {
        return punishData.getOrDefault(uuid, null);
    }

    public PunishButton buttonFromString(String string){
        String[] split = string.split(":");

        Material material = Material.valueOf(split[0]);
        short data = Short.parseShort(split[1]);

        ItemStack item = new ItemStack(material, 1, data);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', split[2]));

        item.setItemMeta(meta);

        int slot = Integer.parseInt(split[3]);
        PunishAction action = PunishAction.valueOf(split[4]);
        int time = Integer.parseInt(split[5]);

        return new PunishButton(item, slot, action, time);
    }

}
