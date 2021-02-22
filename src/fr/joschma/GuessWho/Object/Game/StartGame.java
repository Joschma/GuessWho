package fr.joschma.GuessWho.Object.Game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Seeker;
import fr.joschma.GuessWho.State.StateArena;
import fr.joschma.GuessWho.Timer.GameCountDown;
import fr.joschma.GuessWho.Utilites.DisguisePlayer;
import net.md_5.bungee.api.ChatColor;

public class StartGame {

	public static void startGame(Arena a) {
		// TODO update sign
		if (a == null) {
			return;
		}
		try {
			spawnEntities(a);
			if (a.getPlayers().size() <= 0) {
				return;
			}
			giveTeam(a);
			teleportPlayers(a);
			giveStuff(a);
			a.setState(StateArena.INGAME);
			new GameCountDown(a);
		} catch (Exception e) {
		}

	}

	private static void spawnEntities(Arena a) {
		int y = 0;
		while (y <= a.getNumOfEntity()) {
			for (Block bl : a.getCuboid().blockList()) {
				if (bl.getType().equals(Material.valueOf(a.getMaterial()))) {
					if (y >= a.getNumOfEntity()) {
						return;
					}
					a.getHiderSpawn().getWorld().spawnEntity(bl.getLocation(),
							EntityType.valueOf(a.getDt().toString()));
					y++;
				}
			}
			if (y == 0) {
				for (Player p : a.getPlayers()) {
					p.sendMessage(ChatColor.GRAY + "No block found to spawn " + a.getDt().getEntityType());
					p.sendMessage(ChatColor.DARK_RED + "Stoping");
					new ClearArena(a);
					return;
				}
			}
		}
	}

	private static void giveStuff(Arena a) {
//		for (Player hider : a.getHiders()) {
//			//TODO give stuff + effect
//		}

		for (Player seeker : a.getSeekers()) {
			seeker.getInventory().setItem(0, createIT(Material.WOODEN_SWORD, ChatColor.DARK_PURPLE + "Le Debunker"));
		}
	}

	public static ItemStack createIT(Material ma, String displayName, String... lore) {
		ItemStack it = new ItemStack(ma);
		ItemMeta im = it.getItemMeta();
		if (displayName != null) {
			im.setDisplayName(displayName);
		}
		if (lore != null) {
			im.setLore(Arrays.asList(lore));
		}
		it.setItemMeta(im);
		return it;
	}

	private static void teleportPlayers(Arena a) {
		for (Player hider : a.getHiders()) {
			DisguisePlayer.diguisePlayer(hider, a.getDt());
			hider.teleport(a.getHiderSpawn());
			hider.sendTitle(ChatColor.GREEN + "HIDER", "", 1, 20 * 3, 1);
			hider.sendMessage(ChatColor.GREEN + "Tu es hider");
		}
		for (Player seeker : a.getSeekers()) {
			seeker.teleport(a.getSeekerSpawn());
			seeker.sendTitle(ChatColor.AQUA + "Seeker", "", 1, 20 * 3, 1);
			seeker.sendMessage(ChatColor.AQUA + "Tu es seeker");
		}
	}

	public static void giveTeam(Arena a) {
		List<Player> players = a.getPlayers();
		List<Player> hiders = new ArrayList<Player>();
		List<Player> seeker = new ArrayList<Player>();
		List<Seeker> seekerList = new ArrayList<Seeker>();

		if (players.size() <= 4) {
			int hidder1 = generateRandomInt(0, players.size());
			Player pHidden1 = players.get(hidder1);
			hiders.add(pHidden1);
			
			a.setForgottenHiders(hiders);
			a.setHiders(hiders);
		} else {
			int hidder1 = generateRandomInt(0, players.size());
			int hidder2 = generateRandomInt(0, players.size());
			while (hidder1 == hidder2) {
				hidder2 = generateRandomInt(0, players.size());
			}
			Player pHidden1 = players.get(hidder1);
			Player pHidden2 = players.get(hidder2);
			hiders.add(pHidden1);
			hiders.add(pHidden2);
			
			a.setForgottenHiders(hiders);
			a.setHiders(hiders);
		}
		
		seeker.addAll(players);
		seeker.removeAll(hiders);
		
		for (Player seekerToAdd : seeker) {
			seekerList.add(new Seeker(seekerToAdd, a.getMaxlives()));
		}

		a.setSeekersList(seekerList);
		a.setForgottenSeekers(seeker);
		a.setSeekers(seeker);
	}

	private static int generateRandomInt(int min, int max) {
		int randomNum = ThreadLocalRandom.current().nextInt(min, max);
		return randomNum;
	}
}
