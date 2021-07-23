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

public class CommandFeed implements CommandExecutor {
    private String prefix = "Messages.prefix";
    private PluginCore pluginCore;

    public CommandFeed(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FileConfiguration messages = pluginCore.getFilesLoader().getMessages();
        if (!(sender instanceof Player)) {
            Bukkit.getConsoleSender().sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return false;
        }
        Player p = (Player) sender;
        if(!p.hasPermission("ic.feed")) {
            p.sendMessage(prefix + TextColor.colorized("Messages.no_permission"));
            p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);
            return true;
        }
        if(args.length == 0) {
            p.setFoodLevel(20);
            p.sendMessage(prefix + TextColor.colorized("Messages.feed"));
            p.playSound(p.getLocation(), Sound.EAT, 1, 1);
            return true;
        }
        if(args.length == 1) {
            Player pf = Bukkit.getServer().getPlayer(args[0]);

            if (pf == null) {
                p.sendMessage(prefix + TextColor.colorized("Messages.player_disconnected"));
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 1, 1);
                return true;
            }
            p.setFoodLevel(20);
            p.sendMessage(prefix + TextColor.colorized("&7Has alimentado por completo a &c" + args[0] + ""));

            }else{

                p.sendMessage(prefix + TextColor.colorized("Messages.unknown_command"));
                p.playSound(p.getLocation(), Sound.ANVIL_LAND, 1, 1);

        }
        return false;
    }
}
