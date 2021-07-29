package me.itzdavidvzla.insanecommands.command;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {
    private final PluginCore pluginCore;
    private FileManager sound;

    public HealCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.sound = pluginCore.getFilesLoader().getSounds();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileManager messages = pluginCore.getFilesLoader().getMessages();

        String prefix = messages.getString("Messages.prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ic.heal")) {
            player.sendMessage(prefix + TextColor.colorized("Messages.no_permission"));
            player.playSound(player.getLocation(),
                    Sound.valueOf(sound.getString("Sounds.error.sound")),
                    (float) sound.getDouble("Sounds.error.vol"),
                    (float) sound.getDouble("Sounds.error.pitch"));

            return true;
        }
        if (!(args.length > 0)) {
            player.setFoodLevel(20);
            player.setHealth(20);
            player.sendMessage(prefix + messages.getString("Messages.heal"));
            player.playSound(player.getLocation(),
                    Sound.valueOf(sound.getString("Sounds.heal.sound")),
                    (float) sound.getDouble("Sounds.heal.vol"),
                    (float) sound.getDouble("Sounds.heal.pitch"));

            return true;
        }
        if (!(args.length > 1)) {
            player.sendMessage(prefix + messages.getString("Messages.unknown_command"));
            player.playSound(player.getLocation(),
                    Sound.valueOf(sound.getString("Sounds.unknown_command.sound")),
                    (float) sound.getDouble("Sounds.unknown_command.vol"),
                    (float) sound.getDouble("Sounds.unknown_command.pitch"));
            return true;
        }
        return false;
    }
}
