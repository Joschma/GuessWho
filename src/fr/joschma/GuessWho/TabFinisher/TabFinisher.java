package fr.joschma.GuessWho.TabFinisher;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.ArenaManager;

public class TabFinisher implements TabCompleter {

	List<String> arguments = new ArrayList<String>();

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
		try {
			arguments.clear();
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (arguments.isEmpty()) {
					if (player.hasPermission("GuessWho.admin") || player.isOp()) {
						if (arguments.isEmpty()) {
							arguments.add("create");
							arguments.add("relaod");
							arguments.add("leave");
							arguments.add("remove");
							for (String name : ArenaManager.getArenaNames()) {
								arguments.add("" + name);
							}
						}
					}
					arguments.add("join");
				}
				for (String name : ArenaManager.getArenaNames()) {
					if (args[0].equalsIgnoreCase(name)) {
						List<String> list = new ArrayList<String>();
						if (args.length == 2) {
							list.add("setSpawnHider");
							list.add("setSpawnSeeker");
							list.add("setSpawnLobby");
							list.add("setSpawnEnd");
							list.add("setSpawnSpectator");
							list.add("finish");
							list.add("wand");
							return list;
						} else if (args.length > 2) {
							return null;
						}
					}
				}

				if (args[0].equalsIgnoreCase("join") || args[0].equalsIgnoreCase("remove")) {
					List<String> list = new ArrayList<String>();
					if (args.length == 2) {
						for (String name : ArenaManager.getArenaNames()) {
							list.add("" + name);
						}
					} else if (args.length > 2) {
						return null;
					}
					return list;
				}
				if (player.hasPermission("GuessWho.admin") || player.isOp()) {
					if (args[0].equalsIgnoreCase("create")) {
						List<String> list = new ArrayList<String>();
						if (args.length == 2) {
							for (String name : ArenaManager.getArenaNames()) {
								list.add("" + name);
							}
						} else if (args.length > 2) {
							return null;
						}
						return list;
					}
				}

				return arguments;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
