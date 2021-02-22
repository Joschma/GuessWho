package fr.joschma.GuessWho.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.ArenaManager;

public class onPlayerInventoryClick implements Listener {
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if (e.getWhoClicked() instanceof Player) {
			Player pla = (Player) e.getWhoClicked();
			Arena a = ArenaManager.getArenaPlayer(pla);
			if (a == null) {
				return;
			} else {
				e.setCancelled(true);
			}
		}
	}
}
