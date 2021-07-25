package me.itzdavidvzla.insanecommands.listener;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerQuitListener {

    private final FileManager config;
    private final FileManager quitFile;

    public PlayerQuitListener(PluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.quitFile = pluginCore.getFilesLoader().getJoinLeave();
    }

    @EventHandler
    public void onQuitMessage(PlayerQuitEvent event){
        if (!config.getBoolean("leave-message")) return;

        event.setQuitMessage(quitFile.getString("message_leave")
                .replace("%ic_player%", event.getPlayer().getName()));
    }
}
