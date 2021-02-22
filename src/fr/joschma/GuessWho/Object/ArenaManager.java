package fr.joschma.GuessWho.Object;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.GuessWho;

public class ArenaManager {

	static List<Arena> arenas = new ArrayList<Arena>();
	public static File ArenaNames = new File(GuessWho.pl.getDataFolder(), "ArenaNames.yml");
	public static YamlConfiguration fc = YamlConfiguration.loadConfiguration(ArenaNames);
	static List<String> arenaNames = fc.getStringList("Arena-list");

	public static Arena getArena(String arena) {
		for (Arena a : arenas) {
			if (a.getName().equals(arena)) {
				return a;
			}
		}
		return null;
	}

	public static Arena getArenaPlayer(Player p) {
		for (Arena a : arenas) {
			if (a.getPlayers().contains(p)) {
				return a;
			}
		}
		return null;
	}

	public static List<String> getArenaNames() {
		for (Arena arena : arenas) {
			if (!arenaNames.contains(arena.getName())) {
				arenaNames.add(arena.getName());
			}
		}
		return arenaNames;
	}

	public static void setArenaNames(List<String> arenaNames) {
		ArenaManager.arenaNames = arenaNames;
	}

	public static List<Arena> getArenas() {
		return arenas;
	}

	public static void setArenas(List<Arena> arenas) {
		ArenaManager.arenas = arenas;
	}
}
