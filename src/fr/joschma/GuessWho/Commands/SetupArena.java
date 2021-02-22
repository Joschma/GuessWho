package fr.joschma.GuessWho.Commands;

import java.io.File;
import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.joschma.GuessWho.Listener.PlayerInteractListener;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class SetupArena implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (cmd.getName().equalsIgnoreCase("gw")) {
				String arenaN = args[0];
				if (ArenaManager.getArenaNames().contains(arenaN)) {
					if (args.length == 2) {
						Arena a = ArenaManager.getArena(arenaN);
						File f = a.getFile();
						YamlConfiguration fc = YamlConfiguration.loadConfiguration(f);
						if (args[1].equalsIgnoreCase("setSpawnHider")) {
							fc.set("Arena.Spawns.HiderSpawn",
									p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
									+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
									+ p.getLocation().getYaw());
							a.setHiderSpawn(p.getLocation());
							p.sendMessage(ChatColor.GRAY + "Tu as bien mit le spawn des Hidders");
						} else if (args[1].equalsIgnoreCase("setSpawnSeeker")) {
							fc.set("Arena.Spawns.SeekerSpawn",
									p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
											+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
											+ p.getLocation().getYaw());
							a.setSeekerSpawn(p.getLocation());
							p.sendMessage(ChatColor.GRAY + "Tu as bien mit le spawn des Seekers");
						} else if (args[1].equalsIgnoreCase("setSpawnLobby")) {
							fc.set("Arena.Spawns.LobbySpawn", p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
									+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
									+ p.getLocation().getYaw());
							a.setLobbySpawn(p.getLocation());
							p.sendMessage(ChatColor.GRAY + "Tu as bien mit le spawn du Lobby");
						} else if (args[1].equalsIgnoreCase("setSpawnEnd")) {
							fc.set("Arena.Spawns.EndSpawn", p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
									+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
									+ p.getLocation().getYaw());
							a.setEndSpawn(p.getLocation());
							p.sendMessage(ChatColor.GRAY + "Tu as bien mit le spawn de Fin");
						} else if (args[1].equalsIgnoreCase("setSpawnSpectator")) {
							fc.set("Arena.Spawns.SpectatorSpawn", p.getWorld().getName() + "/" + p.getLocation().getX() + "/" + p.getLocation().getY()
									+ "/" + p.getLocation().getZ() + "/" + p.getLocation().getPitch() + "/"
									+ p.getLocation().getYaw());
							a.setSpectatorSpawn(p.getLocation());
							p.sendMessage(ChatColor.GRAY + "Tu as bien mit le spawn des Spectateurs");
						} else if (args[1].equalsIgnoreCase("finish")) {
							if (a.isFinished()) {
								fc.set("Arena.Finished", false);
								a.setFinished(false);
								p.sendMessage(ChatColor.GRAY + "Tu as bien " + ChatColor.GOLD + "désactivé"
										+ ChatColor.GRAY + " l'arène " + arenaN);
							} else {
								fc.set("Arena.Finished", true);
								a.setFinished(true);
								p.sendMessage(ChatColor.GRAY + "Tu as bien " + ChatColor.GOLD + "activé"
										+ ChatColor.GRAY + " l'arène " + arenaN);
							}
						} else if (args[1].equalsIgnoreCase("wand")) {
							ItemStack it = new ItemStack(Material.BLAZE_ROD);
							ItemMeta im = it.getItemMeta();
							im.setDisplayName(ChatColor.DARK_GREEN + "WandItemFromGuessWho");
							ArrayList<String> lore = new ArrayList<String>();
							lore.add(ChatColor.GRAY + "1 clique gauche");
							lore.add(ChatColor.GRAY + "1 clique droit");
							im.setLore(lore);
							it.setItemMeta(im);
							p.getInventory().setItem(0, it);

							p.sendMessage(ChatColor.RED + "Pour arrêter de placer la zone refaite le commande /gw " + a.getName() + " wand");
							
							if (!PlayerInteractListener.map.containsKey(p)) {
								PlayerInteractListener.map.put(p, a);
							} else {
								PlayerInteractListener.map.remove(p);
								p.sendMessage(ChatColor.RED + "Place la zone");
							}
						}

						try {
							fc.save(f);
						} catch (Exception e) {
							p.sendMessage(ChatColor.RED + "Error");
							return false;
						}
						return true;
					}
					return false;
				} else {
					p.sendMessage(ChatColor.RED + "Arène introuvable");
					return false;
				}
			}
		}
		return false;
	}

}
