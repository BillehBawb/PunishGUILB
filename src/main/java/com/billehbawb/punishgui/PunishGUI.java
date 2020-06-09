package com.billehbawb.punishgui;

import com.billehbawb.punishgui.command.PunishCommand;
import com.billehbawb.punishgui.listener.PunishGUIListener;
import com.billehbawb.punishgui.manager.PunishManager;
import lombok.Getter;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class PunishGUI extends JavaPlugin {

    private static PunishGUI instance;

    private PunishManager punishManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        punishManager = new PunishManager();

        getCommand("punish").setExecutor(new PunishCommand());

        getServer().getPluginManager().registerEvents(new PunishGUIListener(), this);
    }

    @Override
    public void onDisable() {

    }

    public static PunishGUI getInstance() {
        return instance;
    }
}
