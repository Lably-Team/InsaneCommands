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

public class BroadcastCommand implements CommandExecutor {

    private final PluginCore pluginCore;

    public BroadcastCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileManager messages = pluginCore.getFilesLoader().getMessages();
        FileManager sound = pluginCore.getFilesLoader().getSounds();
        String prefix = messages.getString("Messages.prefix");

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return true;
        }

        Player player = (Player) sender;

        if(!(player.hasPermission("ic.broadcast.command"))) {
            player.sendMessage(prefix + messages.getString("Messages.no_permission"));
            player.playSound(
                    player.getLocation(),
                    Sound.valueOf(sound.getString("Sounds.error.sound")),
                    (float) sound.getDouble("Sounds.error.vol"),
                    (float) sound.getDouble("Sounds.error.pitch")

            );
            return true;
        }

        if(!(args.length > 0)) {
            player.sendMessage(messages.getString("Messages.help_command"));
            return true;
        }

        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while (i < args.length) {
            stringBuilder.append(' ').append(args[i]);
            ++i;
        }
        Bukkit.broadcastMessage(messages.getString("Messages.broadcast") + TextColor.colorized(stringBuilder.toString()));

        Bukkit.getOnlinePlayers().forEach(online -> online.playSound(
                online.getLocation(),
                Sound.valueOf(sound.getString("Sounds.broadcast.sound")),
                (float) sound.getDouble("Sounds.broadcast.vol"),
                (float) sound.getDouble("Sounds.broadcast.pitch")));

        return true;
    }
}
