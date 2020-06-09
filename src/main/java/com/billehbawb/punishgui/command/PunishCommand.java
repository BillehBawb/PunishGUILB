package com.billehbawb.punishgui.command;

import com.billehbawb.punishgui.PunishGUI;
import com.billehbawb.punishgui.manager.data.PunishData;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PunishCommand implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return true;
        }

        if (!(sender.hasPermission("punishgui.opengui"))) {
            sender.sendMessage(ChatColor.DARK_RED + "No permission.");
            return true;
        }

        Player player = (Player) sender;

        if (args.length != 2) {
            player.sendMessage(ChatColor.DARK_AQUA + "PunishGUI made by BillehBawb.");
            player.sendMessage(ChatColor.DARK_AQUA + "/punish <username> <reason>");
            return false;
        }

        OfflinePlayer punishedPlayer = Bukkit.getOfflinePlayer(args[0]);
        StringBuilder punishReason = new StringBuilder();

        for (int i = 1; i < args.length; i++) {
            punishReason.append(" ").append(args[i]);
        }

        PunishGUI.getInstance().getPunishManager().getPunishData().put(((Player) sender).getUniqueId(),
                new PunishData(((Player) sender).getUniqueId(), punishedPlayer, punishReason.toString()));
        PunishGUI.getInstance().getPunishManager().openGUI((Player) sender);
        return true;
    }

}
