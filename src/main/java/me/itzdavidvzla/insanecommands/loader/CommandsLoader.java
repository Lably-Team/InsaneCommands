package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.PluginCore;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.command.*;
import me.itzdavidvzla.insanecommands.command.builder.ExecutorBuilder;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeAdventure;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeCreative;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeSpectator;
import me.itzdavidvzla.insanecommands.command.gamemode.GamemodeSurvival;
import me.itzdavidvzla.insanecommands.command.spawn.DelSpawnCommand;
import me.itzdavidvzla.insanecommands.command.spawn.SetSpawnCommand;
import me.itzdavidvzla.insanecommands.command.spawn.SpawnCommand;
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
                new ExecutorBuilder("fly", new FlyCommand(pluginCore)),
                new ExecutorBuilder("feed", new FeedCommand(pluginCore)),
                new ExecutorBuilder("heal", new HealCommand(pluginCore)),
                new ExecutorBuilder("gma", new GamemodeAdventure(pluginCore)),
                new ExecutorBuilder("gmc", new GamemodeCreative(pluginCore)),
                new ExecutorBuilder("gmsp", new GamemodeSpectator(pluginCore)),
                new ExecutorBuilder("gms", new GamemodeSurvival(pluginCore)),
                new ExecutorBuilder("fly", new GamemodeSpectator(pluginCore)),
                new ExecutorBuilder("spawn", new SpawnCommand(pluginCore)),
                new ExecutorBuilder("setspawn", new SetSpawnCommand(pluginCore)),
                new ExecutorBuilder("delspawn", new DelSpawnCommand(pluginCore))
        );
    }

    public void registerCommands(ExecutorBuilder... executorBuilders){

        for (ExecutorBuilder executorBuilder : executorBuilders){
            Bukkit.getPluginCommand(executorBuilder.getCommandName()).setExecutor(executorBuilder.getCommandExecutor());
        }
    }
}
