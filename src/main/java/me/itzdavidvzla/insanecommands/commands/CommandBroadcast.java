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
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		FileConfiguration messages = plugin.getMessages();

		if(!sender.hasPermission("ic.broadcast")) {
			sender.sendMessage(messages.getString("Messages.no_permission"));
			return true;
		}

		if (!(args.length > 0)) {
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.invalid_broadcast")));
			return true;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < args.length; i++) {
			stringBuilder.append(args[i]).append(' ');
		}

		String text = stringBuilder.toString();
		String coloredString = ChatColor.translateAlternateColorCodes('&', text);
		String prefix = ChatColor.translateAlternateColorCodes('&', messages.getString("Messages.broadcast"));
		Bukkit.broadcastMessage(prefix + coloredString);
		return true;
	}
}