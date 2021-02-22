package fr.joschma.GuessWho.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Game.JoinArena;

public class ArenaJoin implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("gw")) {
			if (args[0].equalsIgnoreCase("join")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (args.length == 2) {
						String arenaN = args[1];
						if (ArenaManager.getArenaNames().contains(arenaN)) {
							JoinArena.join(ArenaManager.getArena(arenaN), p);
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
