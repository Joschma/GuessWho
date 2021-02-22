package fr.joschma.GuessWho.Object.Game;

import org.bukkit.GameMode;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.Arena;
import fr.joschma.GuessWho.Object.Other.Cuboid;
import fr.joschma.GuessWho.Timer.GameCountDown;
import fr.joschma.GuessWho.Timer.WinCountDown;
import fr.joschma.GuessWho.Utilites.DisguisePlayer;
import net.md_5.bungee.api.ChatColor;

public class FinishGame {

	public static void HiderWin(Arena a) {
		GameCountDown.stopTimer(a);
		toAll(a);
		for (Player hider : a.getForgottenHiders()) {
			DisguisePlayer.unDiguisePlayer(hider);
			hider.sendTitle(ChatColor.YELLOW + "VICTOIRE", "", 1, 20 * 4, 1);
			hider.sendMessage(ChatColor.YELLOW + "Tu as gagné");
		}
		for (Player seeker : a.getForgottenSeekers()) {
			seeker.sendTitle(ChatColor.RED + "PERDU", "", 1, 20 * 4, 1);
			seeker.sendMessage(ChatColor.RED + "Tu as perdu");
		}
		
		new WinCountDown(a);
	}

	public static void SeekerWin(Arena a) {
		GameCountDown.stopTimer(a);
		toAll(a);
		for (Player seeker : a.getForgottenSeekers()) {
			seeker.sendTitle(ChatColor.YELLOW + "VICTOIRE", "", 1, 20 * 4, 1);
			seeker.sendMessage(ChatColor.YELLOW + "Tu as gagné");
			seeker.sendMessage("ForgottenHiders " + a.getForgottenHiders());
		}
		for (Player hider : a.getForgottenHiders()) {
			DisguisePlayer.unDiguisePlayer(hider);
			hider.sendTitle(ChatColor.RED + "PERDU", "", 1, 20 * 4, 1);
			hider.sendMessage(ChatColor.RED + "Tu as perdu");
		}
		new WinCountDown(a);
	}

	static void toAll(Arena a) {
		a.getPlayers().forEach(p -> {
			p.setGameMode(GameMode.SURVIVAL);
			p.getInventory().clear();
			p.setLevel(0);
			p.getActivePotionEffects().forEach(effect -> {
				p.removePotionEffect(effect.getType());
			});
			p.teleport(a.getLobbySpawn());
		});
		
		Cuboid cu = a.getCuboid();
		if (Math.max(cu.getXWidth(), cu.getZWidth()) <= cu.getHeight()) {
			int height = cu.getHeight() + 1;
			cu.getCenter().getNearbyEntities(height, height, height).forEach(en -> {
				if(en.getType() != EntityType.PLAYER) {
					en.remove();
				}
			});
		} else {
			int max = Math.max(cu.getXWidth(), cu.getZWidth()) + 1;
			cu.getCenter().getNearbyEntities(max, max, max).forEach(en -> {
				if(en.getType() != EntityType.PLAYER) {
					en.remove();
				}
			});
		}
	}
}
