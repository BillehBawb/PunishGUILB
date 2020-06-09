package com.billehbawb.punishgui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class OpenGUI implements CommandExecutor {

    static Player punishedPlayer;
    static OfflinePlayer offlinePunishedPlayer;
    static String punishReason;
    static boolean offline = false;

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player)){
            return false;
        }
        if (!(sender.hasPermission("punishgui.opengui"))) {
            sender.sendMessage(ChatColor.DARK_RED + "No permission.");
            return false;
        }

        Player player = (Player) sender;

        if (args.length != 2){
            player.sendMessage(ChatColor.DARK_AQUA + "PunishGUI made by BillehBawb.");
            player.sendMessage(ChatColor.DARK_AQUA + "/punish <username> <reason>");
            return false;
        }

        punishedPlayer = Bukkit.getPlayer(args[0]);
        if (punishedPlayer == null){
            offlinePunishedPlayer = Bukkit.getOfflinePlayer(args[0]);
            offline = true;
        }
        punishReason = args[1];

        Inventory inv = PunishGUI.buildGUI();
        player.openInventory(inv);

        return false;
    }
}
