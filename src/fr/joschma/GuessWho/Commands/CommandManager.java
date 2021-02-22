package fr.joschma.GuessWho.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.ArenaManager;

public class CommandManager implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gw")) {
				if (args.length == 0) {
					p.sendMessage("/gw create [ar�ne]");
					p.sendMessage("/gw relaod");
					p.sendMessage("/gw join [ar�ne]");
					p.sendMessage("/gw [ar�ne] setSpawnHider");
					p.sendMessage("/gw [ar�ne] setSpawnSeaker");
					p.sendMessage("/gw [ar�ne] setSpawnLobby");
					p.sendMessage("/gw [ar�ne] setSpawnEnd");
					p.sendMessage("/gw [ar�ne] setSpawnSpectator");
					p.sendMessage("/gw [ar�ne] Finish");
					return true;
				}
				if (sender.isOp() || sender.hasPermission("GuessWho.admin")) {
					if (args.length == 2) {
						if (args[0].equalsIgnoreCase("create")) {
							new CreateArena().onCommand(sender, cmd, commandLabel, args);
							return true;
						} else if (ArenaManager.getArenaNames().contains(args[0])) {
							new SetupArena().onCommand(sender, cmd, commandLabel, args);
							return true;
						} else if (args[0].equalsIgnoreCase("remove")) {
							new RemoveArena().onCommand(sender, cmd, commandLabel, args);
						} else if (args[0].equalsIgnoreCase("join")) {
							new ArenaJoin().onCommand(sender, cmd, commandLabel, args);
							return true;
						}
					} else if (args.length == 1) {
						if (args[0].equalsIgnoreCase("relaod")) {
							new ReloadArenas().onCommand(sender, cmd, commandLabel, args);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
}
