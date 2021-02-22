package fr.joschma.GuessWho.Object;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import fr.joschma.GuessWho.Object.Other.Cuboid;
import fr.joschma.GuessWho.State.StateArena;
import me.libraryaddict.disguise.disguisetypes.DisguiseType;

public class Arena {

	String name;
	File file;
	boolean finished;
	int maxPlayer;
	int minPlayer;
	int maxLobbyWaitTime;
	int maxGameTime;
	int maxAppreciateTime;
	int maxlives;
	DisguiseType dt;
	StateArena state;
	List<Player> players = new ArrayList<Player>();
	List<Player> spectator = new ArrayList<Player>();
	List<Player> hiders = new ArrayList<Player>();
	List<Player> seekers = new ArrayList<Player>();
	List<Seeker> seekersList = new ArrayList<Seeker>();
	int NumOfEntity;
	Location seekerSpawn;
	Location hiderSpawn;
	Location lobbySpawn;
	Location endSpawn;
	Location spectatorSpawn;
	Cuboid cuboid;
	String material;
	Location loc1;
	Location loc2;
	List<Player> forgottenSeekers = new ArrayList<Player>();
	List<Player> forgottenHiders = new ArrayList<Player>();

	public Arena(String name, File file, boolean finished, int maxPlayer, int minPlayer, int maxLobbyWaitTime,
			int maxGameTime, int maxAppreciateTime, int maxlives, DisguiseType dt, StateArena state,
			List<Player> players, List<Player> spectator, List<Player> hiders, List<Player> seekers,
			List<Seeker> seekersList, int numOfEntity, Location seekerSpawn, Location hiderSpawn,
			Location lobbySpawn, Location endSpawn, Location spectatorSpawn, Cuboid cuboid, String material,
			Location loc1, Location loc2, List<Player> forgottenSeekers, List<Player> forgottenHiders) {
		super();
		this.name = name;
		this.file = file;
		this.finished = finished;
		this.maxPlayer = maxPlayer;
		this.minPlayer = minPlayer;
		this.maxLobbyWaitTime = maxLobbyWaitTime;
		this.maxGameTime = maxGameTime;
		this.maxAppreciateTime = maxAppreciateTime;
		this.maxlives = maxlives;
		this.dt = dt;
		this.state = state;
		this.players = players;
		this.spectator = spectator;
		this.hiders = hiders;
		this.seekers = seekers;
		this.seekersList = seekersList;
		this.NumOfEntity = numOfEntity;
		this.seekerSpawn = seekerSpawn;
		this.hiderSpawn = hiderSpawn;
		this.lobbySpawn = lobbySpawn;
		this.endSpawn = endSpawn;
		this.spectatorSpawn = spectatorSpawn;
		this.cuboid = cuboid;
		this.material = material;
		this.loc1 = loc1;
		this.loc2 = loc2;
		this.forgottenSeekers = forgottenSeekers;
		this.forgottenHiders = forgottenHiders;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public void addPlayer(Player player) {
		this.players.add(player);
	}

	public void addHider(Player hider) {
		this.players.add(hider);
	}

	public void addSeeker(Player seeker) {
		this.players.add(seeker);
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMaxPlayer() {
		return maxPlayer;
	}

	public void setMaxPlayer(int maxPlayer) {
		this.maxPlayer = maxPlayer;
	}

	public int getMinPlayer() {
		return minPlayer;
	}

	public void setMinPlayer(int minPlayer) {
		this.minPlayer = minPlayer;
	}

	public int getMaxLobbyWaitTime() {
		return maxLobbyWaitTime;
	}

	public void setMaxLobbyWaitTime(int maxLobbyWaitTime) {
		this.maxLobbyWaitTime = maxLobbyWaitTime;
	}

	public int getMaxGameTime() {
		return maxGameTime;
	}

	public void setMaxGameTime(int maxGameTime) {
		this.maxGameTime = maxGameTime;
	}

	public int getMaxAppreciateTime() {
		return maxAppreciateTime;
	}

	public void setMaxAppreciateTime(int maxAppreciateTime) {
		this.maxAppreciateTime = maxAppreciateTime;
	}

	public int getMaxlives() {
		return maxlives;
	}

	public void setMaxlives(int maxlives) {
		this.maxlives = maxlives;
	}

	public DisguiseType getDt() {
		return dt;
	}

	public void setDt(DisguiseType dt) {
		this.dt = dt;
	}

	public StateArena getState() {
		return state;
	}

	public void setState(StateArena state) {
		this.state = state;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}

	public List<Player> getHiders() {
		return hiders;
	}

	public List<Player> getSpectator() {
		return spectator;
	}

	public void setSpectator(List<Player> spectator) {
		this.spectator = spectator;
	}

	public void setHiders(List<Player> hiders) {
		this.hiders = hiders;
	}

	public List<Player> getSeekers() {
		return seekers;
	}

	public void setSeekers(List<Player> seekers) {
		this.seekers = seekers;
	}

	public List<Seeker> getSeekersList() {
		return seekersList;
	}

	public void setSeekersList(List<Seeker> seekersList) {
		this.seekersList = seekersList;
	}

	public Location getSeekerSpawn() {
		return seekerSpawn;
	}

	public void setSeekerSpawn(Location seekerSpawn) {
		this.seekerSpawn = seekerSpawn;
	}

	public Location getHiderSpawn() {
		return hiderSpawn;
	}

	public void setHiderSpawn(Location hiderSpawn) {
		this.hiderSpawn = hiderSpawn;
	}

	public Location getLobbySpawn() {
		return lobbySpawn;
	}

	public void setLobbySpawn(Location lobbySpawn) {
		this.lobbySpawn = lobbySpawn;
	}

	public Location getEndSpawn() {
		return endSpawn;
	}

	public void setEndSpawn(Location endSpawn) {
		this.endSpawn = endSpawn;
	}

	public Location getSpectatorSpawn() {
		return spectatorSpawn;
	}

	public void setSpectatorSpawn(Location spectatorSpawn) {
		this.spectatorSpawn = spectatorSpawn;
	}

	public int getNumOfEntity() {
		return NumOfEntity;
	}

	public void setNumOfEntity(int NumOfEntity) {
		this.NumOfEntity = NumOfEntity;
	}

	public Cuboid getCuboid() {
		return cuboid;
	}

	public void setCuboid(Cuboid cuboid) {
		this.cuboid = cuboid;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public Location getLoc1() {
		return loc1;
	}

	public void setLoc1(Location loc1) {
		this.loc1 = loc1;
	}

	public Location getLoc2() {
		return loc2;
	}

	public void setLoc2(Location loc2) {
		this.loc2 = loc2;
	}

	public List<Player> getForgottenSeekers() {
		return forgottenSeekers;
	}

	public void setForgottenSeekers(List<Player> forgottenSeekers) {
		this.forgottenSeekers = forgottenSeekers;
	}

	public List<Player> getForgottenHiders() {
		return forgottenHiders;
	}

	public void setForgottenHiders(List<Player> forgottenHiders) {
		this.forgottenHiders = forgottenHiders;
	}

}
