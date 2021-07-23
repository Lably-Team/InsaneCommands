package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.command.MainCommand;
import me.itzdavidvzla.insanecommands.command.builder.ExecutorBuilder;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new ExecutorBuilder("defaultcommand", new MainCommand(pluginCore)));
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
