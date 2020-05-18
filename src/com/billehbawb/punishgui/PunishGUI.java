package com.billehbawb.punishgui;

import com.google.common.collect.Lists;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class PunishGUI extends JavaPlugin {

    public static List<GUIButton> buttons;
    public enum Action { IPBAN, BAN, MUTE, PARDON }

    public void onEnable(){
        getCommand("punish").setExecutor(new OpenGUI());

        PluginManager pluginManager = getServer().getPluginManager();
        pluginManager.registerEvents(new GUIClick(), this);
        pluginManager.addPermission(new Permission("punishgui.opengui"));

        getConfig().options().copyDefaults(true);
        saveConfig();

        loadButtons();
    }

    public void loadButtons(){
        buttons = Lists.newArrayList();

        for (String s : getConfig().getStringList("Buttons")) {
            GUIButton button = buttonFromString(s);
            buttons.add(button);
        }
    }

    public GUIButton buttonFromString(String string){
        String[] split = string.split(":");

        Material material = Material.valueOf(split[0]);
        short data = Short.parseShort(split[1]);

        ItemStack item = new ItemStack(material, 1, data);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', split[2]));

        item.setItemMeta(meta);

        GUIButton button = new GUIButton();
        button.item = item;
        button.slot = Integer.parseInt(split[3]);
        button.action = Action.valueOf(split[4]);
        button.time = Integer.parseInt(split[5]);

        return button;
    }

    public static Inventory buildGUI(){
        Inventory inventory = Bukkit.createInventory(null, 27, "PunishGUI");

        for (GUIButton button : buttons) {
            inventory.setItem(button.getSlot(), button.getItem());
        }

        return inventory;
    }
}
