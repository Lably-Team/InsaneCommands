package me.itzdavidvzla.insanecommands.command;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FeedCommand implements CommandExecutor {
    private final FileManager sound;
    private final FileManager messages;

    public FeedCommand(PluginCore pluginCore) {
        this.sound = pluginCore.getFilesLoader().getSounds();
        this.messages = pluginCore.getFilesLoader().getMessages();

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args){

        String prefix = messages.getString("Messages.prefix");

        if (!(sender instanceof Player)) {
            sender.sendMessage(TextColor.colorized("&8[&cInsane&4Commands&8] &7This command not be executed from console"));
            return true;
        }

        Player player = (Player) sender;
        if(!player.hasPermission("ic.feed")) {
            player.sendMessage(prefix + TextColor.colorized("Messages.no_permission"));
            player.playSound(player.getLocation(),
                    sound.getSound("Sounds.error.sound"),
                    (float) sound.getDouble("Sounds.error.vol"),
                    (float) sound.getDouble("Sounds.error.pitch"));

            return true;
        }
        if(!(args.length > 0)) {
            player.setFoodLevel(20);
            player.sendMessage(prefix + messages.getString("Messages.feed"));
            player.playSound(player.getLocation(),
                    sound.getSound("Sounds.feed.sound"),
                    (float) sound.getDouble("Sounds.feed.vol"),
                    (float) sound.getDouble("Sounds.feed.pitch"));
            return true;
        }
        if(!(args.length > 1)) {
            player.sendMessage(prefix + messages.getString("Messages.unknown_command"));
            player.playSound(player.getLocation(),
                    sound.getSound("Sounds.unknown_command.sound"),
                    (float) sound.getDouble("Sounds.unknown_command.vol"),
                    (float) sound.getDouble("Sounds.unknown_command.pitch"));
            return true;
        }
        return false;
    }
}
