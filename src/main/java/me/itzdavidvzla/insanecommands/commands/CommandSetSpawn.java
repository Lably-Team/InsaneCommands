package me.itzdavidvzla.insanecommands.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import me.itzdavidvzla.insanecommands.InsaneCommands;

public class CommandSetSpawn implements CommandExecutor{

	private InsaneCommands plugin;
	public CommandSetSpawn(InsaneCommands plugin){
		this.plugin = plugin;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!(sender instanceof Player)) {
			Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7comando no puede ser ejecutable desde la consola!"));
			return false;
		}
			
		Player player = (Player)sender;
			if(player.hasPermission("ic.setspawn")){
				if(args.length == 0){
					Location l = player.getLocation();
					double x = l.getX();
					double y = l.getY();
					double z = l.getZ();
					String world = l.getWorld().getName();
					float yaw = l.getYaw();
					float pitch = l.getPitch();
					FileConfiguration warps_and_spawn = plugin.getWarps();
					warps_and_spawn.set("Spawn.x", x);
					warps_and_spawn.set("Spawn.y", y);
					warps_and_spawn.set("Spawn.z", z);
					warps_and_spawn.set("Spawn.world", world);
					warps_and_spawn.set("Spawn.yaw", yaw);
					warps_and_spawn.set("Spawn.pitch", pitch);
					plugin.saveWarps();
					
					
					
					player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&cInsane&4Commands&8] &7El Spawn del servidor ha sido establecido con exito&8."));
		        	player.playSound(player.getLocation(), Sound.NOTE_PLING, 1.0F, 1.0F);
		        	return true;
		        	
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
