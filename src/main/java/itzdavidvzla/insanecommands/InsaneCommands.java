package itzdavidvzla.insanecommands;

import itzdavidvzla.insanecommands.commands.CommandBroadcast;
import itzdavidvzla.insanecommands.commands.CommandFeed;
import itzdavidvzla.insanecommands.commands.CommandFly;
import itzdavidvzla.insanecommands.commands.CommandGamemodeAdventure;
import itzdavidvzla.insanecommands.commands.CommandGamemodeCreative;
import itzdavidvzla.insanecommands.commands.CommandGamemodeSpectator;
import itzdavidvzla.insanecommands.commands.CommandGamemodeSurvival;
import itzdavidvzla.insanecommands.commands.CommandHeal;
import itzdavidvzla.insanecommands.commands.CommandSetSpawn;
import itzdavidvzla.insanecommands.commands.CommandSpawn;
import itzdavidvzla.insanecommands.listeners.ChatListener;
import itzdavidvzla.insanecommands.listeners.JoinListener;
import itzdavidvzla.insanecommands.listeners.QuitListener;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class InsaneCommands extends JavaPlugin{
	private FileConfiguration messages = null;
	private File messagesFile = null;
	private FileConfiguration warps = null;
	private File warpsFile = null;
	private String rutaConfig;
	private PluginDescriptionFile pdffile = getDescription();
	public String name = pdffile.getName();
	public String version = pdffile.getVersion();
  
  	public void onEnable(){ //sus
  		Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Plugin &aactivado&8."));
  		registerCommands();
  		registerMessages();
  		registerListeners();
  		registerWarps();
  		
  	}
  
  	public void onDisable(){
	  Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Plugin &cdesactivado&8."));
  	}
  
  	public void registerCommands(){
  		this.getCommand("ic").setExecutor(new CommandMain(this));
  		this.getCommand("bc").setExecutor(new CommandBroadcast(this));
  		this.getCommand("fly").setExecutor(new CommandFly(this));
  		this.getCommand("feed").setExecutor(new CommandFeed(this));
  		this.getCommand("heal").setExecutor(new CommandHeal(this));
  		this.getCommand("gmc").setExecutor(new CommandGamemodeCreative(this));
  		this.getCommand("gms").setExecutor(new CommandGamemodeSurvival(this));
  		this.getCommand("gme").setExecutor(new CommandGamemodeSpectator(this));
  		this.getCommand("gma").setExecutor(new CommandGamemodeAdventure(this));
  		this.getCommand("spawn").setExecutor(new CommandSpawn(this));
  		this.getCommand("setspawn").setExecutor(new CommandSetSpawn(this));
  	}	
  
  	public void registerListeners(){
  		PluginManager pm = getServer().getPluginManager();
  		pm.registerEvents(new JoinListener(this), this);
  		pm.registerEvents(new QuitListener(this), this);
  		pm.registerEvents(new ChatListener(this), this);
  	}
 	
  	public void registerWarps(){
		  warpsFile = new File(this.getDataFolder(), "warps_and_spawn.yml");
		  if(!warpsFile.exists()){
		    	this.getWarps().options().copyDefaults(true);
				saveWarps();
		    }
	  }
	  public void saveWarps() {
		 try {
			 warps.save(warpsFile);
		 } catch (IOException e) {
			 e.printStackTrace();
	 	}
	 }

	  public FileConfiguration getWarps() {
		    if (warps == null) {
		        reloadWarps();
		    }
		    return warps;
		}

	  public void reloadWarps() {
		    if (warps == null) {
		    	warpsFile = new File(getDataFolder(), "warps_and_spawn.yml");
		    }
		    warps = YamlConfiguration.loadConfiguration(warpsFile);

		    Reader defConfigStream;
			try {
				defConfigStream = new InputStreamReader(this.getResource("warps_and_spawn.yml"), "UTF8");
				if (defConfigStream != null) {
			        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
			        warps.setDefaults(defConfig);
			    }
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}	    
		}
  	
	public FileConfiguration getMessages(){
		if(messages == null){
			reloadMessages();
		}
		return messages;
	}
 
	public void reloadMessages(){
		if(messages == null){
			messagesFile = new File(getDataFolder(),"messages.yml");
		}
		messages = YamlConfiguration.loadConfiguration(messagesFile);
		Reader defConfigStream;
		try{
			defConfigStream = new InputStreamReader(this.getResource("messages.yml"),"UTF8");
			if(defConfigStream != null){
				YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
				messages.setDefaults(defConfig);
			}			
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
 
	public void saveMessages(){
		try{
			messages.save(messagesFile);			
		}catch(IOException e){
			e.printStackTrace();
		}
	}
 
	public void registerMessages(){
		messagesFile = new File(this.getDataFolder(),"messages.yml");
		if(!messagesFile.exists()){
			this.getMessages().options().copyDefaults(true);
			saveMessages();
		}
	}
}
