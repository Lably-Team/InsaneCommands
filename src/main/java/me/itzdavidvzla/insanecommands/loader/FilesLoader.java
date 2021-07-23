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

    public FilesLoader(InsaneCommands plugin){
        this.plugin = plugin;
    }
    @Override
    public void load() {
        config = new FileManager(plugin, "config.yml");
        messages = new FileManager(plugin, "messages.yml");
        warps_and_spawn = new FileManager(plugin, "warps_and_spawn.yml");
        sounds = new FileManager(plugin, "sounds.yml");

    }

    public FileManager getConfig() { return config; }

    public FileManager getMessages() {
        return messages;
    }

    public FileManager getWarps() { return warps_and_spawn; }

    public FileManager getSounds() { return sounds; }

}
