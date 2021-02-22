package fr.joschma.GuessWho.Commands;

import java.io.File;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class ReloadArenas implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gw")) {
				if(args.length == 1) {
					if(args[0].equals("relaod")) {
						for(Arena a : ArenaManager.getArenas()) {
							if(a == null) {
								return false;
							}
							File f = a.getFile();
							YamlConfiguration.loadConfiguration(f);
						}
						p.sendMessage(ChatColor.GRAY + "Toutes les arènes on été reload");
						return true;
					}
				}
			}
		}
		return false;
	}
	
}
