package me.itzdavidvzla.insanecommands.command;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.manager.FileManager;
import me.itzdavidvzla.insanecommands.utils.TextColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class MainCommand implements CommandExecutor {

    private final PluginCore pluginCore;
    private final FileManager config;
    private final FileManager messages;
    private final FileManager sound;
    private final FileManager joinFile;
    private final FileManager spawnFile;
    private final FileManager warps;

    public MainCommand(PluginCore pluginCore) {
        this.pluginCore = pluginCore;
        this.config = pluginCore.getFilesLoader().getConfig();
        this.joinFile = pluginCore.getFilesLoader().getJoinLeave();
        this.sound = pluginCore.getFilesLoader().getSounds();
        this.messages = pluginCore.getFilesLoader().getMessages();
        this.spawnFile = pluginCore.getFilesLoader().getSpawn();
        this.warps = pluginCore.getFilesLoader().getWarps();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String prefix = messages.getString("Messages.prefix");

        if(!(sender.hasPermission("ic.general.command"))) {
            sender.sendMessage(prefix + messages.getString("Messages.no_permission"));
            return true;
        }

        if(!(args.length > 0)) {
            sender.sendMessage(prefix + messages.getString("Messages.help_command"));
            return true;
        }

        switch (args[0].toLowerCase()){
            case "about":
                sender.sendMessage(prefix + TextColor.colorized("&8- &cVersion: &6" + pluginCore.getPlugin().getDescription().getVersion()));
                sender.sendMessage(TextColor.colorized("&cAuthor: &c" + pluginCore.getPlugin().getDescription().getAuthors()));
                break;
            case "reload":
                config.reload();
                messages.reload();
                sound.reload();
                joinFile.reload();
                spawnFile.reload();
                warps.reload();
                sender.sendMessage(prefix + messages.getString("Messages.plugin_reload"));
                break;
            default:
                for (String line : messages.getStringList("Messages.help_lore")) {
                    sender.sendMessage(line);
                }
                break;
        }
        return true;
    }
}



