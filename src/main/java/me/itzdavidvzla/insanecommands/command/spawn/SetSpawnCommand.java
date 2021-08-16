package me.itzdavidvzla.insanecommands.command.spawn;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetSpawnCommand implements CommandExecutor {

    private final FileManager messages;
    private final FileManager sound;
    private final FileManager spawn;

    public SetSpawnCommand(PluginCore pluginCore) {
        this.messages = pluginCore.getFilesLoader().getMessages();
        this.sound = pluginCore.getFilesLoader().getSounds();
        this.spawn = pluginCore.getFilesLoader().getSpawn();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        String prefix = messages.getString("Messages.prefix");

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("ic.setspawn.command"))) {
            player.sendMessage(prefix + messages.getString("Messages.no_permission"));
            player.playSound(
                    player.getLocation(),
                    Sound.valueOf(sound.getString("Sounds.error.sound")),
                    (float) sound.getDouble("Sounds.error.vol"),
                    (float) sound.getDouble("Sounds.error.pitch")

            );
            return true;
        }

        if (spawn.contains("Spawn.world")) {
            player.sendMessage(prefix + messages.getString("Messages.spawn_exist"));
            return true;
        }

        spawn.set("Spawn.world", player.getLocation().getWorld().getName());
        spawn.set("Spawn.x",  player.getLocation().getX());
        spawn.set("Spawn.y",  player.getLocation().getY());
        spawn.set("Spawn.z",  player.getLocation().getZ());
        spawn.set("Spawn.yaw", (double) player.getLocation().getYaw());
        spawn.set("Spawn.pitch", (double) player.getLocation().getPitch());
        spawn.save();
        player.sendMessage(prefix + messages.getString("Messages.spawn_set"));

        return true;
    }
}
