package com.billehbawb.punishgui.manager.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.OfflinePlayer;

import java.util.UUID;

@AllArgsConstructor
@Getter
public class PunishData {

    private final UUID player;
    private final OfflinePlayer target;
    private final String reason;

}
