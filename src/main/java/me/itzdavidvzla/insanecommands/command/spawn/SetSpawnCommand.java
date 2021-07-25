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

    private final PluginCore pluginCore;

    public SetSpawnCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        FileManager messages = pluginCore.getFilesLoader().getMessages();
        FileManager sound = pluginCore.getFilesLoader().getSounds();
        FileManager spawn = pluginCore.getFilesLoader().getSpawn();
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




        return true;
    }
}
