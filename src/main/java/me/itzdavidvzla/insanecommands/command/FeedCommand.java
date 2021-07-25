package me.itzdavidvzla.insanecommands.command;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    private final PluginCore pluginCore;

    public FeedCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
        FileManager messages = pluginCore.getFilesLoader().getMessages();

        String prefix = messages.getString("Messages.prefix");

        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return true;
        }

        Player player = (Player) sender;
        if(!player.hasPermission("ic.feed")) {
            player.sendMessage(prefix + TextColor.colorized("Messages.no_permission"));
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
            return true;
        }
        if(!(args.length > 0)) {
            player.setFoodLevel(20);
            player.sendMessage(prefix + messages.getString("Messages.feed"));
            player.playSound(player.getLocation(), Sound.EAT, 1, 1);
            return true;
        }
        if(!(args.length > 1)) {
            player.sendMessage(prefix + messages.getString("Messages.unknown_command"));
            player.playSound(player.getLocation(), Sound.ANVIL_LAND, 1, 1);
            return true;
        }

        Player target = Bukkit.getServer().getPlayerExact(args[0]);

        if (target == null) {
            player.sendMessage(prefix + messages.getString("Messages.player_disconnected"));
            player.playSound(player.getLocation(), Sound.ENDERDRAGON_HIT, 1, 1);
            return true;
        }
        player.setFoodLevel(20);
        player.sendMessage(prefix + TextColor.colorized("&7Has alimentado por completo a &c" + args[0] + ""));
        return true;
    }
}
