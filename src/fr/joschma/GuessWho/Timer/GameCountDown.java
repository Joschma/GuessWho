package fr.joschma.GuessWho.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Game.FinishGame;

public class GameCountDown {

	Integer[] t = { 180, 120, 90, 60, 30, 15, 10, 5 };

	public GameCountDown(Arena a) {
		startCountDown(a);
	}

	static int taskID;

	private void startCountDown(Arena a) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(GuessWho.pl, new Runnable() {

			int countDown = a.getMaxGameTime();

			@Override
			public void run() {
				if (countDown == 0) {
					FinishGame.HiderWin(a);
					countDown--;
					return;
				}

				for (Player p : a.getPlayers()) {
					for (int i : t) {
						if (countDown == i) {
							p.sendMessage(ChatColor.GRAY + "Fin de la partie dans " + ChatColor.GOLD + countDown
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
	}
}
