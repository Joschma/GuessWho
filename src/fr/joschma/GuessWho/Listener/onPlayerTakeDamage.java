package fr.joschma.GuessWho.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Seeker;
import fr.joschma.GuessWho.Object.Spectator;
import fr.joschma.GuessWho.State.StateArena;
import fr.joschma.GuessWho.Utilites.DisguisePlayer;

public class onPlayerTakeDamage implements Listener {

	@EventHandler
	public void onPlayerDamage(EntityDamageByEntityEvent e) {
		if (e.getDamager() instanceof Player) {
			Player killer = (Player) e.getDamager();
			Arena a = ArenaManager.getArenaPlayer(killer);
			if (a == null) {
				return;
			} else {
				if (a.getState().equals(StateArena.INGAME)) {
					if (e.getEntity() instanceof Player) {
						e.setCancelled(true);
						Player killed = (Player) e.getEntity();
						DisguisePlayer.unDiguisePlayer(killed);
						killer.sendMessage(ChatColor.GRAY + "Tu as réussit à trouver un joueur !");
						killed.sendMessage(ChatColor.GRAY + "Tu as été trouver !");
						new Spectator(a, killed);
						return;
					} else {
						if (a.getSeekers().contains(killer)) {
							Seeker seeker = Seeker.valueOf(killer, a);
							if (seeker.getLives() == 1) {
								killer.sendMessage(ChatColor.GRAY + "Tu as tué trop d'inocent");
								new Spectator(a, killer);
								return;
							} else {
								int lives = seeker.getLives() - 1;
								seeker.setLives(lives);
								killer.sendMessage(ChatColor.GRAY + "Tu as encore " + ChatColor.GOLD + seeker.getLives()
										+ ChatColor.GRAY + " vies");
								return;
							}
						}
					}
				}
			}
		}

		if (e.getEntity() instanceof Player) {
			Player damageTaken = (Player) e.getEntity();
			Arena a = ArenaManager.getArenaPlayer(damageTaken);
			if (a == null) {
				return;
			}
			if (a.getState().equals(StateArena.INGAME)) {
				if (e.getCause() != DamageCause.ENTITY_ATTACK) {
					e.setCancelled(true);
				}
			} else {
				e.setCancelled(true);
			}
		}
	}

}
