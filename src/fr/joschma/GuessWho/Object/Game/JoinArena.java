package fr.joschma.GuessWho.Object.Game;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.State.StateArena;
import fr.joschma.GuessWho.Timer.LobbyCountDown;

public class JoinArena {

	private static boolean canJoin(Arena a, Player p) {
		if (a.isFinished()) {
			if (a.getPlayers().size() <= a.getMaxPlayer()) {
				if (!a.getState().equals(StateArena.INGAME) && !a.getState().equals(StateArena.CLEARING)) {
					if (a.getLobbySpawn() != null) {
						if (a.getHiderSpawn() != null) {
							if (a.getSeekerSpawn() != null) {
								if (a.getEndSpawn() != null) {
									if (a.getSpectatorSpawn() != null) {
										if(a.getCuboid() != null) {
											for (Arena as : ArenaManager.getArenas()) {
												if (as.getPlayers().contains(p)) {
													p.sendMessage(ChatColor.RED + "Tu es déjà en jeu");
													return false;
												}
											}
											return true;
										} else {
											p.sendMessage(ChatColor.RED
													+ "L'arène n'est pas fini. Il manque la zone");
											return false;
										}
									} else {
										p.sendMessage(ChatColor.RED
												+ "L'arène n'est pas fini. Il manque le spawn des Spectateurs");
										return false;
									}
								} else {
									p.sendMessage(ChatColor.RED + "L'arène n'est pas fini. Il manque le spawn de Fin");
									return false;
								}
							} else {
								p.sendMessage(ChatColor.RED + "L'arène n'est pas fini. Il manque le spawn des Seekers");
								return false;
							}
						} else {
							p.sendMessage(ChatColor.RED + "L'arène n'est pas fini. Il manque le spawn des Hidders");
							return false;
						}
					} else {
						p.sendMessage(ChatColor.RED + "L'arène n'est pas fini. Il manque le spawn du Lobby");
						return false;
					}
				} else {
					p.sendMessage(ChatColor.RED + "L'arène est en cours de jeu");
					return false;
				}
			} else {
				p.sendMessage(ChatColor.RED + "L'arène est pleine");
				return false;
			}
		} else {
			p.sendMessage(ChatColor.RED + "L'arène n'est pas fini");
			return false;
		}
	}

	public static void join(Arena a, Player p) {
		if (canJoin(a, p)) {
			// TODO update sign
			p.teleport(a.getLobbySpawn());
			a.addPlayer(p);
			for (Player pla : a.getPlayers()) {
				pla.sendMessage(ChatColor.GOLD + p.getName().toUpperCase() + ChatColor.GRAY + " a rejoint le jeu, "
						+ +a.getPlayers().size() + " joueur");
			}
			Bukkit.getScheduler().runTaskLater(GuessWho.pl, new Runnable() {
				public void run() {
					giveStuff(p);
				}
			}, 20L);
			
			if (a.getState().equals(StateArena.CLEARED)) {
				if (a.getPlayers().size() >= a.getMinPlayer()) {
					a.setState(StateArena.WATTING);
					new LobbyCountDown(a);
				} else {
					p.sendMessage(ChatColor.GRAY + "En attente de joueur");
				}
			}
		}
	}

	private static void giveStuff(Player p) {
		p.getInventory().clear();
		p.setGameMode(GameMode.SURVIVAL);
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
		BookMeta bookM = (BookMeta) book.getItemMeta();
		bookM.setTitle(ChatColor.DARK_RED + "Règles");
		bookM.addPage("A venir");
		bookM.setDisplayName("Règles");
		bookM.setAuthor("Jesus");
		book.setItemMeta(bookM);
		ItemStack leave = new ItemStack(Material.BLACK_BED);
		ItemMeta leaveM = leave.getItemMeta();
		leaveM.setDisplayName("§4Clic ici pour quitter");
		leave.setItemMeta(leaveM);
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, Integer.MAX_VALUE, 255));
		p.getInventory().setItem(7, book);
		p.getInventory().setItem(8, leave);
	}
}
