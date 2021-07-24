package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.command.CommandFeed;
import me.itzdavidvzla.insanecommands.command.MainCommand;
import me.itzdavidvzla.insanecommands.command.builder.ExecutorBuilder;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private final PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands();
                new ExecutorBuilder("feed", new CommandFeed(pluginCore));

        registerCommands(
                new ExecutorBuilder("ic", new MainCommand(pluginCore)),
                new ExecutorBuilder("feed", new CommandFeed(pluginCore)));
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
