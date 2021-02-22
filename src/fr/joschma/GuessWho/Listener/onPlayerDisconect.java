package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Game.QuitGame;

public class onPlayerDisconect implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Player pla = event.getPlayer();
		Arena a = ArenaManager.getArenaPlayer(pla);
		if (a == null) {
			return;
		}
		if (a.getPlayers().contains(pla)) {
			new QuitGame(a, pla);
		}
	}
}
