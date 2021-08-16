package me.itzdavidvzla.insanecommands.listener;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final FileManager config;
    private final FileManager joinFile;
    private final FileManager sound;

    public PlayerJoinListener(PluginCore pluginCore) {
        this.config = pluginCore.getFilesLoader().getConfig();
        this.joinFile = pluginCore.getFilesLoader().getJoinLeave();
        this.sound = pluginCore.getFilesLoader().getSounds();

    }

    @EventHandler
    public void onJoinMessage(PlayerJoinEvent event) {
        if (!config.getBoolean("Config.join-message")) return;

        event.setJoinMessage(joinFile.getString("message_join")
                .replace("%ic_player%", event.getPlayer().getName()));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(
                online.getLocation(),
                Sound.valueOf(sound.getString("Sounds.join.sound")),
                (float) sound.getDouble("Sounds.join.vol"),
                (float) sound.getDouble("Sounds.join.pitch")));
        }

    @EventHandler
    public void onJoinMotd(PlayerJoinEvent event) {
        if (!joinFile.getBoolean("welcome_motd_module")) return;

        for (String line : joinFile.getStringList("welcome_motd")) {

            event.getPlayer().sendMessage(line
                    .replace("%ic_player%", event.getPlayer().getName()));
        }

    }
}
