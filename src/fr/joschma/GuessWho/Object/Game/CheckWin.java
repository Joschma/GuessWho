package fr.joschma.GuessWho.Object.Game;

import fr.joschma.GuessWho.Object.Arena;

public class CheckWin {
	public CheckWin(Arena a) {
		checkWin(a);
	}

	private void checkWin(Arena a) {
		if (a.getHiders().size() == 0) {
			FinishGame.SeekerWin(a);
		} else if (a.getSeekers().size() == 0) {
			FinishGame.HiderWin(a);
		} else {
			return;
		}
	}
}
