package tp1.p2.logic;

import java.util.List;

import tp1.p2.control.Level;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Zombie;

public interface GameWorld {

	public static final int NUM_ROWS = 4;

	public static final int NUM_COLS = 8;

	void playerQuits(boolean exit);
	
	public String positionToString(int a, int b);
	
	boolean isPositionEmpty(int a, int b);
	
	public int getSuncoins();
	
	public int getCycle();
	
	public void anadirSuncoins(int cantidad);
	
	void reset(Level level, long seed) throws GameException;
	
	void reset() throws GameException;
	
	public List<GameItem> getObjectInPosition(int col, int row);
	
	public void zombieDied();
	
	public void addItem(GameObject obj);
	
	public boolean anadirPlanta(Plant plant) throws GameException;
	
	void update() throws GameException;
	
    public Level getLevel();
	
	public long getSeed();

	public List<GameItem> getLimits(int col, int row);

	boolean anadirZombie(Zombie z) throws GameException;
	
	public void addSun();

	boolean catchCoins(int col, int row) throws GameException;
	
	public void addAction(GameAction act);
	
	public boolean ZombiesGana();
	
	public boolean acabaJuego();
	
	public void haveZombiesWin();
	
	void tryToBuy(int cost) throws GameException;

	void checkValidPlantPosition(int col, int row) throws GameException;

	void checkValidZombiePosition(int col, int row) throws GameException;
	
	public void addPoints(int points);
	
	public int getScore();
	public int getRecord();

}
