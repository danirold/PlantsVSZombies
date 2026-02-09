package tp1.p2.logic.gameobjects;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieFactory {
	
	private static final List<Zombie> AVAILABLE_ZOMBIES = Arrays.asList(
			new ZombieComun(),
			new BucketHead(),
			new Sporty(),
			new ExplosiveZombie()
		);
	
	public static List<Zombie> getAvailableZombies() {
		return Collections.unmodifiableList(AVAILABLE_ZOMBIES);
	}
	
	public static boolean isValidZombie(int ZombieType) {
		for (Zombie z : AVAILABLE_ZOMBIES) {
			if (z.matchZombie(ZombieType)) {
				return true;
			}
		}

		return false;
	}
	
	public int size() { 
		return AVAILABLE_ZOMBIES.size();
	}
	
	public static Zombie SpawnZombie(int ZombieType, GameWorld game, int col, int row) throws GameException  {
		for (Zombie z : AVAILABLE_ZOMBIES) {
			if (z.matchZombie(ZombieType)) {
				return z.create(col, row, game.getCycle(), game);
			}
		}
		throw new GameException(Messages.INVALID_GAME_OBJECT);
		
	}

}
