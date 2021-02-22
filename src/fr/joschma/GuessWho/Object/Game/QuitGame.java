package fr.joschma.GuessWho.Object.Game;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.State.StateArena;
import fr.joschma.GuessWho.Timer.LobbyCountDown;
import fr.joschma.GuessWho.Utilites.DisguisePlayer;

public class QuitGame {

	public QuitGame(Arena a, Player p) {
		playerQuitGame(a, p);
	}

	private void playerQuitGame(Arena a, Player p) {
		List<Player> players = a.getPlayers();
		players.remove(p);
		a.setPlayers(players);
		
		for(Player pla : a.getPlayers()) {
			pla.sendMessage(ChatColor.GOLD + p.getName().toUpperCase() + ChatColor.GRAY + " a quitté le jeu");
		}
		
		if (a.getState().equals(StateArena.INGAME)) {
			DisguisePlayer.unDiguisePlayer(p);
			FinishGame.toAll(a);
			
		} else if (a.getState().equals(StateArena.WATTING)) {
			// TODO update sign
			if (a.getPlayers().size() < a.getMinPlayer()) {
				LobbyCountDown.stopTimer(a);
				a.setState(StateArena.CLEARED);
				for(Player pla : a.getPlayers()) {
					pla.sendMessage(ChatColor.GRAY + "En attente de joueur");
				}
			}
		}
		p.sendMessage(ChatColor.GRAY + "Tu as bien quitté le jeu");
	}
}
