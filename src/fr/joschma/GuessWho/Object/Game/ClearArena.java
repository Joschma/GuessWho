package fr.joschma.GuessWho.Object.Game;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Seeker;
import fr.joschma.GuessWho.State.StateArena;
import net.md_5.bungee.api.ChatColor;

public class ClearArena {
	
	List<Player> clear = new ArrayList<Player>();
	List<Seeker> clearList = new ArrayList<Seeker>();
	
	public ClearArena(Arena a) {
		clearArena(a);
	}
	
	private void clearArena(Arena a) {
		a.setState(StateArena.CLEARING);
		a.setForgottenHiders(clear);
		a.setForgottenSeekers(clear);
		
		a.setSpectator(clear);
		
		a.setHiders(clear);
		a.setSeekers(clear);
		a.setSeekersList(clearList);
		
		for(Player p : a.getPlayers()) {
			p.teleport(a.getEndSpawn());
			p.sendMessage(ChatColor.LIGHT_PURPLE + "Merci d'avoir joué sur le serveur Helvetis");
		}
		
		a.setPlayers(clear);
		a.setState(StateArena.CLEARED);
		//TODO update sign
	}
}
