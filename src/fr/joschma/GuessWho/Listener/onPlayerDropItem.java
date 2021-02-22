package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class onPlayerDropItem implements Listener {
	@EventHandler
	public void onItemDrop(PlayerDropItemEvent e) {
		Player pla = e.getPlayer();
		Arena a = ArenaManager.getArenaPlayer(pla);
		if (a == null) {
			return;
		} else {
			e.setCancelled(true);
		}
	}
}
