package fr.joschma.GuessWho.Utilites;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.libraryaddict.disguise.disguisetypes.DisguiseType;
import me.libraryaddict.disguise.disguisetypes.MobDisguise;

public class DisguisePlayer {

	public static void diguisePlayer(Player p, DisguiseType dt) {
		MobDisguise mobDisguise = new MobDisguise(dt);
		mobDisguise.setEntity(p);
		mobDisguise.startDisguise();
	}

	public static void unDiguisePlayer(Player p) {
		Bukkit.getServer().dispatchCommand(p.getServer().getConsoleSender(), "undisguiseplayer " + p.getName());
//		PlayerDisguise playerDisguise = new PlayerDisguise(p);
//		playerDisguise.removeDisguise();
	}
}
