package fr.joschma.GuessWho.Object;

import org.bukkit.entity.Player;

public class Seeker {
	
	Player player;
	int lives;
	
	public Seeker(Player player, int lives) {
		super();
		this.player = player;
		this.lives = lives;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}
	
	public static Seeker valueOf(Player player, Arena a) {
		for(Seeker seaker : a.getSeekersList()) {
			if(seaker.getPlayer().equals(player)) {
				return seaker;
			}
		}
		return null;
	}
}
