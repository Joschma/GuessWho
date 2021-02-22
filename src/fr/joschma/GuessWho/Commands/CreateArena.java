package fr.joschma.GuessWho.Commands;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Seeker;
import fr.joschma.GuessWho.State.StateArena;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class CreateArena implements CommandExecutor {

	GuessWho pl = GuessWho.pl;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gw")) {
				if (args[0].equalsIgnoreCase("create")) {
					if (args.length == 2) {
						String arenaN = args[1];
						if (!ArenaManager.getArenaNames().contains(arenaN)) {
							if (!pl.getDataFolder().exists()) {
								pl.getDataFolder().mkdir();
							}

							File a = new File(pl.getDataFolder(), args[1] + ".yml");
							File ArenaNames = new File(pl.getDataFolder(), "ArenaNames.yml");

							if (!a.exists()) {
								try {
									a.createNewFile();
								} catch (IOException ex) {
									Bukkit.getServer().getLogger()
											.severe("Could not create ArenaSettings " + arenaN + ".yml!");
									p.sendMessage("Could not create " + arenaN + ".yml!");
									ex.printStackTrace();
									return false;
								}
							}
							if (!ArenaNames.exists()) {
								try {
									ArenaNames.createNewFile();
								} catch (IOException ex) {
									Bukkit.getServer().getLogger()
											.severe("Could not create ArenaSettings ArenaNames.yml!");
									p.sendMessage("Could not create ArenaNames.yml!");
									ex.printStackTrace();
									return false;
								}
							}

							YamlConfiguration fc = YamlConfiguration.loadConfiguration(a);
							YamlConfiguration fcn = YamlConfiguration.loadConfiguration(ArenaNames);

							Arena arena = new Arena(arenaN, a, false, 8, 2, 60, 60, 5, 3, DisguiseType.VILLAGER,
									StateArena.CLEARED, new ArrayList<Player>(), new ArrayList<Player>(),
									new ArrayList<Player>(), new ArrayList<Player>(), new ArrayList<Seeker>(),
									10, null, null, null, null, null, null, "BRICKS", null, null, new ArrayList<Player>(), new ArrayList<Player>());

							List<String> arenaList = ArenaManager.getArenaNames();
							arenaList.add(arenaN);
							ArenaManager.setArenaNames(arenaList);

							fc.set("Arena.Name", arena.getName());
							fc.set("Arena.Finished", arena.isFinished());
							fc.set("Arena.Game.MaxPlayer", arena.getMaxPlayer());
							fc.set("Arena.Game.MinPlayer", arena.getMinPlayer());
							fc.set("Arena.Game.MaxLives", arena.getMaxlives());
							fc.set("Arena.Game.DisguiseType", arena.getDt().toString());
							fc.set("Arena.Game.NumberOfEntities", arena.getNumOfEntity());
							fc.set("Arena.Timer.MaxLobbyWaitTime", arena.getMaxLobbyWaitTime());
							fc.set("Arena.Timer.MaxGameTime", arena.getMaxGameTime());
							fc.set("Arena.Timer.MaxAppreciateTime", arena.getMaxAppreciateTime());
							fc.set("Arena.Material", arena.getMaterial());
							fc.set("Arena.Spawns", "");
							fc.set("Arena.Spawns.HiderSpawn", null);
							fc.set("Arena.Spawns.SeekerSpawn", null);
							fc.set("Arena.Spawns.LobbySpawn", null);
							fc.set("Arena.Spawns.EndSpawn", null);
							fc.set("Arena.Spawns.SpectatorSpawn", null);
							fc.set("Arena.Zone.Loc1", null);
							fc.set("Arena.Zone.Loc2", null);

							fcn.set("ArenaList", ArenaManager.getArenaNames());

							try {
								fc.save(a);
								fcn.save(ArenaNames);
							} catch (Exception e) {
								p.sendMessage(ChatColor.RED + "Error");
								return false;
							}

							arena.setFile(a);

							List<Arena> arenas = ArenaManager.getArenas();
							arenas.add(arena);
							ArenaManager.setArenas(arenas);

							p.sendMessage(ChatColor.GRAY + "Tu as bien créé l'arène " + arenaN);
							return true;
						} else {
							p.sendMessage(ChatColor.RED + "Cette arene existe déjà");
						}
					} else {
						p.sendMessage(ChatColor.RED + "/gw create [arenaName]");
					}
				}
			}
		}
		return false;
	}
}
