package fr.joschma.GuessWho.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Game.StartGame;

public class LobbyCountDown {

	Integer[] t = { 180, 120, 90, 60, 30, 15, 10, 5, 4, 3, 2, 1 };

	public LobbyCountDown(Arena a) {
		startCountDown(a);
	}

	static int taskID;

	private void startCountDown(Arena a) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(GuessWho.pl, new Runnable() {
			int countDown = a.getMaxLobbyWaitTime();

			@Override
			public void run() {
				if (countDown == 0) {
					StartGame.startGame(a);
					stopTimer(a);
					countDown--;
					return;
				}

				for (Player p : a.getPlayers()) {
					p.setLevel(countDown);
					for (int i : t) {
						if (countDown == i) {
							p.sendMessage(ChatColor.GRAY + "La partie commence dans " + ChatColor.GOLD + countDown
									+ ChatColor.GRAY + " secondes !");
						}
					}
				}
				countDown--;
			}
		}, 0L, 20L);
	}

	public static void stopTimer(Arena a) {
		Bukkit.getScheduler().cancelTask(taskID);
		for (Player p : a.getPlayers()) {
			p.setLevel(0);
		}
	}
}
