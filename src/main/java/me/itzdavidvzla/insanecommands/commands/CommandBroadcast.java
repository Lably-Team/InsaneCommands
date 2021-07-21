package me.itzdavidvzla.insanecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class CommandBroadcast implements CommandExecutor {
	
	private final InsaneCommands plugin;
  	public CommandBroadcast(InsaneCommands plugin){
  		this.plugin = plugin;
  }
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args){
    if(sender.hasPermission("ic.broadcast") && command.getName().equalsIgnoreCase("bc")){
    	if(args.length == 0){
    	  FileConfiguration messages = plugin.getMessages();
    	  sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.invalid_broadcast")));
    	  return true;
    	} 
    	FileConfiguration messages = plugin.getMessages();
    	StringBuilder str = new StringBuilder();
      	for (int i = 0; i < args.length; i++)
        str.append(String.valueOf(args[i]) + " "); 
      	String s = str.toString();
      	String coloredString = ChatColor.translateAlternateColorCodes('&', s);
      	String prefix = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.broadcast"));
      	Bukkit.broadcast(String.valueOf(prefix) + coloredString, s);
    	} 
    return true;
	}
}