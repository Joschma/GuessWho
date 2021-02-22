package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class onPlayerBreakBlock implements Listener {

	@EventHandler
	public void onBreakBlock(BlockBreakEvent e) {
		Player pla = e.getPlayer();
		Arena a = ArenaManager.getArenaPlayer(pla);
		if (a == null && !PlayerInteractListener.map.containsKey(pla)) {
			return;
		} else {
			e.setCancelled(true);
		}
	}
}
