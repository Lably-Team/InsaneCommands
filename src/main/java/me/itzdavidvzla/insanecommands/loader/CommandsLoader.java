package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.command.BroadcastCommand;
import me.itzdavidvzla.insanecommands.command.FeedCommand;
import me.itzdavidvzla.insanecommands.command.HealCommand;
import me.itzdavidvzla.insanecommands.command.MainCommand;
import me.itzdavidvzla.insanecommands.command.builder.ExecutorBuilder;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeAdventure;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeCreative;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeSpectator;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeSurvival;
import org.bukkit.Bukkit;

public class CommandsLoader implements Loader {

    private final PluginCore pluginCore;

    public CommandsLoader(PluginCore pluginCore){
        this.pluginCore = pluginCore;
    }

    @Override
    public void load() {
        registerCommands(
                new ExecutorBuilder("insanecommands", new MainCommand(pluginCore)),
                new ExecutorBuilder("broadcast", new BroadcastCommand(pluginCore)),
                new ExecutorBuilder("fly", new GamemodeSpectator(pluginCore)),
                new ExecutorBuilder("feed", new FeedCommand(pluginCore)),
                new ExecutorBuilder("heal", new HealCommand(pluginCore)),
                new ExecutorBuilder("gma", new GamemodeAdventure(pluginCore)),
                new ExecutorBuilder("gmc", new GamemodeCreative(pluginCore)),
                new ExecutorBuilder("gmsp", new GamemodeSpectator(pluginCore)),
                new ExecutorBuilder("gms", new GamemodeSurvival(pluginCore)),
                new ExecutorBuilder("fly", new GamemodeSpectator(pluginCore))
        );
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
