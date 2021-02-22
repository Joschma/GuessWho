package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Other.Cuboid;
import fr.joschma.GuessWho.State.StateArena;

public class onPlayerMoveListener implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Player p = e.getPlayer();
		Arena a = ArenaManager.getArenaPlayer(p);
		
		if(a != null) {
			Cuboid cu = a.getCuboid();
			if(!cu.isIn(p)) {
				if(a.getSpectator().contains(p)) {
					p.teleport(a.getSpectatorSpawn());
				} else if(a.getSeekers().contains(p)) {
					p.teleport(a.getSeekerSpawn());
				} else if(a.getHiders().contains(p)) {
					p.teleport(a.getHiderSpawn());
				} else if (a.getState().equals(StateArena.WATTING) || a.getState().equals(StateArena.CLEARED)){
					p.teleport(a.getLobbySpawn());
				}
			}
		}
	}
}
