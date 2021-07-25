package me.itzdavidvzla.insanecommands.loader;

import me.itzdavidvzla.insanecommands.InsaneCommands;
import me.itzdavidvzla.insanecommands.api.Loader;
import me.itzdavidvzla.insanecommands.manager.FileManager;

public class FilesLoader implements Loader {

    private final InsaneCommands plugin;
    private FileManager config;
    private FileManager messages;
    private FileManager spawn;
    private FileManager sounds;
    private FileManager joinAndLeave;
    private FileManager warps;

    public FilesLoader(InsaneCommands plugin) {
        this.plugin = plugin;
    }

    @Override
    public void load() {
        config = new FileManager(plugin, "config.yml");
        messages = new FileManager(plugin, "messages.yml");
        spawn = new FileManager(plugin, "spawn.yml");
        warps = new FileManager(plugin, "warps.yml");
        sounds = new FileManager(plugin, "sounds.yml");
        joinAndLeave = new FileManager(plugin, "join_and_leave.yml");

    }


    public FileManager getConfig() { return config; }

    public FileManager getMessages() { return messages; }

    public FileManager getSpawn() { return spawn; }

    public FileManager getSounds() { return sounds; }

    public FileManager getJoinLeave() { return joinAndLeave; }

    public FileManager getWarps() {
        return warps;
    }

}
