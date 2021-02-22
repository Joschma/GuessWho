package fr.joschma.GuessWho.Commands;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class RemoveArena implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gw")) {
			if (args[0].equalsIgnoreCase("remove")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length == 2) {
						String arenaN = args[1];
						if (ArenaManager.getArenaNames().contains(arenaN)) {
							Arena a = ArenaManager.getArena(arenaN);
							File f = a.getFile();
							f.delete();
							List<String> names = ArenaManager.getArenaNames();
							List<Arena> arenas = ArenaManager.getArenas();
							names.remove(arenaN);
							arenas.remove(a);
							ArenaManager.setArenaNames(names);
							ArenaManager.setArenas(arenas);
							
							File ArenaNames = new File(GuessWho.pl.getDataFolder(), "ArenaNames.yml");
							
							YamlConfiguration fcn = YamlConfiguration.loadConfiguration(ArenaNames);
							fcn.set("ArenaList", names);
							
							try {
								fcn.save(ArenaNames);
							} catch (IOException ex) {
								ex.printStackTrace();
							}
							p.sendMessage(ChatColor.RED + "Tu as bien suprimmé l'arène " + arenaN);
							return true;
						} else {
							p.sendMessage(ChatColor.RED + "Cette arène n'existe pas");
							return false;
						}
					} else {
						p.sendMessage(ChatColor.RED + "/join [arene]");
					}
				}
			}
		}
		return false;
	}
}
