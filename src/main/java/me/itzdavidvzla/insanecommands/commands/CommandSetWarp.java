package me.itzdavidvzla.insanecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class CommandSetWarp {

	private InsaneCommands plugin;
	public CommandSetWarp(InsaneCommands plugin) {
		this.plugin = plugin;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    	if (!(sender instanceof Player)) {
    	Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7comando no puede ser ejecutable desde la consola!"));
      	return false;
    } 
    Player player = (Player)sender;
    if(player.hasPermission("ic.setwarp")) {
    	if(args.length == 0) {
    		FileConfiguration messages = plugin.getMessages();
    		FileConfiguration warps_and_spawn = plugin.getWarps();
    		if(warps_and_spawn.contains("Spawn.x")){
    			double x = Double.valueOf(warps_and_spawn.getString("Spawn.x"));
        		double y = Double.valueOf(warps_and_spawn.getString("Spawn.y"));
        		double z = Double.valueOf(warps_and_spawn.getString("Spawn.z"));
        		float yaw = Float.valueOf(warps_and_spawn.getString("Spawn.yaw"));
        		float pitch = Float.valueOf(warps_and_spawn.getString("Spawn.pitch"));
        		World world = plugin.getServer().getWorld(warps_and_spawn.getString("Spawn.world"));
        		Location l = new Location(world, x, y, z, yaw, pitch);
        		player.teleport(l);
        		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Te teletransportaste al &cspawn&8."));
        		player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
        		return true;
    				
    			}else{
    				player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Debes establecer el spawn del servidor para ejecutar este comando&8."));
    				return true;
    			}
    		}
    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7Este comando no se encuentra disponible!"));
    		player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
    	}else{
    		player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7No tienes permisos para ejecutar ese comando!"));
    		player.playSound(player.getLocation(), Sound.ANVIL_USE, 1.0F, 1.0F);
		}
    	return false;
    }	
}
