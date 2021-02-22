package fr.joschma.GuessWho.Object;

import java.util.List;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import fr.joschma.GuessWho.Object.Game.CheckWin;

public class Spectator {
	
	public Spectator(Arena a, Player p) {
		p.sendMessage("constructor " + a.getForgottenHiders());
		setSpec(a, p);
	}
	
	public void setSpec(Arena a, Player p) {
		p.getInventory().clear();
		
		for (PotionEffect effect : p.getActivePotionEffects()) {
			p.removePotionEffect(effect.getType());
		}
		
		if(a.getHiders().contains(p)) {
			List<Player> hiders = a.getHiders();
			p.sendMessage("before forgotten " + a.getForgottenHiders());
			p.sendMessage("before hiders " + hiders);
			hiders.remove(p);
			p.sendMessage("after forgotten " + a.getForgottenHiders());
			p.sendMessage("after hiders " + hiders);
			a.setHiders(hiders);
		}
		
		if(a.getSeekers().contains(p)) {
			List<Player> seeker = a.getSeekers();
			seeker.remove(p);
			a.setSeekers(seeker);
			
			List<Seeker> seekerList = a.getSeekersList();
			for(Seeker s : seekerList) {
				if(p.equals(s.getPlayer())) {
					seekerList.remove(s);
					a.setSeekersList(seekerList);
					break;
				}
			}
		}
		
		p.setGameMode(GameMode.SPECTATOR);
		p.teleport(a.getSpectatorSpawn());
		List<Player> spectator = a.getSpectator();
		spectator.add(p);
		a.setSpectator(spectator);
		
		p.sendMessage("spec " + a.getForgottenHiders());
		
		new CheckWin(a);
	}
}
