package me.itzdavidvzla.insanecommands.listener;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.util.ResourceBundle;

public class PlayerQuitListener {

    private final FileManager config;
    private final FileManager quitFile;
    private FileManager sound;

    public PlayerQuitListener(PluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.quitFile = pluginCore.getFilesLoader().getJoinLeave();
        this.sound = pluginCore.getFilesLoader().getSounds();
    }

    @EventHandler
    public void onQuitMessage(PlayerQuitEvent event){
        if (!config.getBoolean("Config.leave-message")) return;

        event.setQuitMessage(quitFile.getString("message_leave")
                .replace("%ic_player%", event.getPlayer().getName()));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(
                online.getLocation(),
                Sound.valueOf(sound.getString("Sounds.leave.sound")),
                (float) sound.getDouble("Sounds.leave.vol"),
                (float) sound.getDouble("Sounds.leave.pitch")));
    }
}
