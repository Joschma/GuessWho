package fr.joschma.GuessWho;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.joschma.GuessWho.Commands.CommandManager;
import fr.joschma.GuessWho.Listener.PlayerInteractListener;
import fr.joschma.GuessWho.Listener.onPlayerBreakBlock;
import fr.joschma.GuessWho.Listener.onPlayerDisconect;
import fr.joschma.GuessWho.Listener.onPlayerDropItem;
import fr.joschma.GuessWho.Listener.onPlayerInventoryClick;
import fr.joschma.GuessWho.Listener.onPlayerMoveListener;
import fr.joschma.GuessWho.Listener.onPlayerPlaceBlock;
import fr.joschma.GuessWho.Listener.onPlayerTakeDamage;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Seeker;
import fr.joschma.GuessWho.Object.Game.ClearArena;
import fr.joschma.GuessWho.Object.Other.Cuboid;
import fr.joschma.GuessWho.State.StateArena;
import fr.joschma.GuessWho.TabFinisher.TabFinisher;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class GuessWho extends JavaPlugin {

	// TODO gui
	// TODO make signs
	// TODO BUG picht yall

	public static GuessWho pl;

	public Location stringToLoc(String loc) {
		if (loc != null) {
			if (loc.contains("/")) {
				String[] locs = loc.split("/");
				Location signLoc = new Location(Bukkit.getServer().getWorld(locs[0]), Double.parseDouble(locs[1]),
						Double.parseDouble(locs[2]), Double.parseDouble(locs[3]), Float.parseFloat(locs[4]),
						Float.parseFloat(locs[5]));
				return signLoc;
			}
		}
		return null;
	}

	@Override
	public void onEnable() {
		pl = this;
		this.getServer().getPluginManager().registerEvents(new PlayerInteractListener(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerBreakBlock(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerPlaceBlock(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerDisconect(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerDropItem(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerInventoryClick(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerTakeDamage(), this);
		this.getServer().getPluginManager().registerEvents(new onPlayerMoveListener(), this);

		this.getCommand("gw").setTabCompleter(new TabFinisher());

		this.getCommand("gw").setExecutor(new CommandManager());

		recreateArena();
	}

	@Override
	public void onDisable() {
		//TODO make all player quit game
		for(Arena a : ArenaManager.getArenas()) {
			if(a.getPlayers().size() != 0) {
				new ClearArena(a);
			}
		}
		super.onDisable();
	}

	public void recreateArena() {
		File ArenaNames = new File(getDataFolder(), "ArenaNames.yml");
		YamlConfiguration fcn = YamlConfiguration.loadConfiguration(ArenaNames);
		ArenaManager.setArenaNames(fcn.getStringList("ArenaList"));

		for (String s : ArenaManager.getArenaNames()) {
			File file = new File(getDataFolder(), s + ".yml");

			YamlConfiguration fc = YamlConfiguration.loadConfiguration(file);

			String name = fc.getString("Arena.Name");
			Boolean activated = fc.getBoolean("Arena.Finished");
			int maxPlayer = fc.getInt("Arena.Game.MaxPlayer");
			int minPlayer = fc.getInt("Arena.Game.MinPlayer");
			int maxLives = fc.getInt("Arena.Game.MaxLives");
			DisguiseType dt = DisguiseType.valueOf(fc.getString("Arena.Game.DisguiseType"));
			int NofEntity = fc.getInt("Arena.Game.NumberOfEntities");
			int maxLobbyWaitTime = fc.getInt("Arena.Timer.MaxLobbyWaitTime");
			int maxGameTime = fc.getInt("Arena.Timer.MaxGameTime");
			int maxAppreciateTime = fc.getInt("Arena.Timer.MaxAppreciateTime");
			Location hiderSpawn = stringToLoc(fc.getString("Arena.Spawns.HiderSpawn"));
			Location seekerSpawn = stringToLoc(fc.getString("Arena.Spawns.SeekerSpawn"));
			Location lobbySpawn = stringToLoc(fc.getString("Arena.Spawns.LobbySpawn"));
			Location endSpawn = stringToLoc(fc.getString("Arena.Spawns.EndSpawn"));
			Location spectatorSpawn = stringToLoc(fc.getString("Arena.Spawns.SpectatorSpawn"));
			Location loc1 = stringToLoc(fc.getString("Arena.Zone.Loc1"));
			Location loc2 = stringToLoc(fc.getString("Arena.Zone.Loc2"));
			String material = fc.getString("Arena.Material");
			Cuboid cuboid = null;

			if (loc1 != null && loc2 != null) {
				cuboid = new Cuboid(loc1, loc2);
			}

			Arena a = new Arena(name, file, activated, maxPlayer, minPlayer, maxLobbyWaitTime, maxGameTime,
					maxAppreciateTime, maxLives, dt, StateArena.CLEARED, new ArrayList<Player>(),
					new ArrayList<Player>(), new ArrayList<Player>(), new ArrayList<Player>(), new ArrayList<Seeker>(),
					NofEntity, seekerSpawn, hiderSpawn, lobbySpawn, endSpawn, spectatorSpawn, cuboid, material, loc1,
					loc2, new ArrayList<Player>(), new ArrayList<Player>());

			List<Arena> arenas = ArenaManager.getArenas();
			arenas.add(a);
			ArenaManager.setArenas(arenas);
		}
	}
}
