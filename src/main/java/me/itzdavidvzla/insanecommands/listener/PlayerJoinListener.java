package me.itzdavidvzla.insanecommands.listener;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FileManager config;
    private final FileManager joinFile;

    public PlayerJoinListener(PluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.joinFile = pluginCore.getFilesLoader().getJoinLeave();
    }

    @EventHandler
    public void onJoinMessage(PlayerJoinEvent event) {
        if (!config.getBoolean("join-message")) return;

        event.setJoinMessage(joinFile.getString("message_join")
                .replace("%ic_player%", event.getPlayer().getName()));
    }

    @EventHandler
    public void onJoinMotd(PlayerJoinEvent event) {
        if (!joinFile.getBoolean("welcome_motd-module")) return;

        for (String line : joinFile.getStringList("welcome_motd")) {

            event.getPlayer().sendMessage(line
                    .replace("%player%", event.getPlayer().getName()));
        }

    }
}
