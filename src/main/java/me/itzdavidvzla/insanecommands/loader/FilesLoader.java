package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.InsaneCommands;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.manager.FileManager;

public class FilesLoader implements Loader {

    private final InsaneCommands plugin;
    private FileManager config;
    private FileManager messages;
    private FileManager warps_and_spawn;
    private FileManager sounds;
    private FileManager join_and_leave;

    public FilesLoader(InsaneCommands plugin){
        this.plugin = plugin;
    }
    @Override
    public void load() {
        config = new FileManager(plugin, "config.yml");
        messages = new FileManager(plugin, "messages.yml");
        warps_and_spawn = new FileManager(plugin, "warps_and_spawn.yml");
        sounds = new FileManager(plugin, "sounds.yml");
        join_and_leave = new FileManager(plugin, "join_and_leave.yml");

    }

    public FileManager getConfig() { return config; }

    public FileManager getMessages() { return messages; }

    public FileManager getWarpsSpawn() { return warps_and_spawn; }

    public FileManager getSounds() { return sounds; }

    public FileManager getJoinLeave() { return join_and_leave; }

}
