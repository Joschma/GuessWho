package fr.joschma.GuessWho.Listener;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Game.QuitGame;
import fr.joschma.GuessWho.Object.Other.Cuboid;

public class PlayerInteractListener implements Listener {

	public static HashMap<Player, Arena> map = new HashMap<Player, Arena>();
	GuessWho pl = GuessWho.pl;

	@EventHandler
	public void onPlayerInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();

		if (e.getItem() != null) {
			if (e.getItem().getType().equals(Material.BLACK_BED)) {
				Arena a = ArenaManager.getArenaPlayer(p);
				if (a != null) {
					new QuitGame(a, p);
				}
			}

			if (map.containsKey(p)) {
				Arena a = map.get(p);
				if (a != null) {
					File f = a.getFile();
					YamlConfiguration fc = YamlConfiguration.loadConfiguration(f);

					Location loc1 = pl.stringToLoc(fc.getString("Arena.Zone.Loc1"));
					Location loc2 = pl.stringToLoc(fc.getString("Arena.Zone.Loc2"));

					if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
						loc1 = e.getClickedBlock().getLocation();
						a.setLoc1(loc1);
						fc.set("Arena.Zone.Loc1",
								p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
										+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
										+ p.getLocation().getYaw());
						p.sendMessage(ChatColor.GRAY + "Tu as mis la première position");
					} else if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
						loc2 = e.getClickedBlock().getLocation();
						a.setLoc2(loc2);
						fc.set("Arena.Zone.Loc2",
								p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
										+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
										+ p.getLocation().getYaw());
						p.sendMessage(ChatColor.GRAY + "Tu as mis la seconde position");
					}

					p.sendMessage("loc1 " + loc1);
					p.sendMessage("loc2 " + loc2);

					if (loc1 != null && loc2 != null) {
						Cuboid cuboid = new Cuboid(loc1, loc2);
						a.setCuboid(cuboid);
						p.sendMessage(ChatColor.GRAY + "Tu as fini le setup de la zone");
						ItemStack clear = new ItemStack(Material.AIR);
						p.getInventory().setItem(0, clear);
						loc1 = null;
						loc2 = null;
						map.remove(p);
					}

					try {
						fc.save(f);
					} catch (IOException ex) {
						p.sendMessage(ChatColor.RED + "ERREUR !");
						ex.printStackTrace();
					}
					e.setCancelled(true);
				}
			}
		}
	}
}
