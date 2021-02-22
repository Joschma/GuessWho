package fr.joschma.GuessWho.Timer;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import fr.joschma.GuessWho.GuessWho;
import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Game.ClearArena;

public class WinCountDown {
	
	Integer[] t = { 10, 9, 8, 7, 6, 5, 4, 3, 2, 1 };

	public WinCountDown(Arena a) {
		startCountDown(a);
	}

	int taskID;

	private void startCountDown(Arena a) {
		BukkitScheduler scheduler = Bukkit.getServer().getScheduler();
		taskID = scheduler.scheduleSyncRepeatingTask(GuessWho.pl, new Runnable() {

			int countDown = a.getMaxAppreciateTime();

			@Override
			public void run() {
				if (countDown == 0) {
					new ClearArena(a);
					stopTimer();
					countDown--;
					return;
				}

				for (Player p : a.getPlayers()) {
					p.setLevel(countDown);
					for (int i : t) {
						if (countDown == i) {
							p.sendMessage(ChatColor.GRAY + "Téléportation dans " + ChatColor.GOLD + countDown
									+ ChatColor.GRAY + " secondes !");
						}
					}
				}
				countDown--;
			}
		}, 0L, 20L);
	}

	public void stopTimer() {
		Bukkit.getScheduler().cancelTask(taskID);
	}
}
