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
					p.sendMessage("/gw create [arène]");
					p.sendMessage("/gw relaod");
					p.sendMessage("/gw join [arène]");
					p.sendMessage("/gw [arène] setSpawnHider");
					p.sendMessage("/gw [arène] setSpawnSeaker");
					p.sendMessage("/gw [arène] setSpawnLobby");
					p.sendMessage("/gw [arène] setSpawnEnd");
					p.sendMessage("/gw [arène] setSpawnSpectator");
					p.sendMessage("/gw [arène] Finish");
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
