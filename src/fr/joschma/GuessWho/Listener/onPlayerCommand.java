package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;
import fr.joschma.GuessWho.Object.Game.QuitGame;

public class onPlayerCommand implements Listener {

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		Player pla = e.getPlayer();
		Arena a = ArenaManager.getArenaPlayer(pla);
		if (a == null) {
			return;
		} else {
			if (e.getMessage().startsWith("/hub") || e.getMessage().startsWith("/lobby")
					|| e.getMessage().startsWith("/spawn") || e.getMessage().startsWith("/gw leave")) {
				new QuitGame(a, pla);
				return;
			} else {
				pla.sendMessage("Tu es en partie tu n'es pas autorisé à utiliser cette comande");
				e.setCancelled(true);
			}
		}
	}
}
