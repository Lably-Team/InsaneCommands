package me.itzdavidvzla.insanecommands.command;

import me.itzdavidvzla.insanecommands.PluginCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class DefaultCommand implements CommandExecutor{

    private PluginCore pluginCore;

    public DefaultCommand(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }
}
