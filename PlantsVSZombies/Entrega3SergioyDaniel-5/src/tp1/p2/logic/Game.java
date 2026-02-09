package tp1.p2.logic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Random;

import tp1.p2.control.Level;
import tp1.p2.logic.actions.GameAction;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.control.exceptions.NotCatchablePositionException;
import tp1.p2.control.exceptions.NotEnoughCoinsException;
import tp1.p2.logic.gameobjects.GameObject;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.view.Messages;
import tp1.p2.control.Command;

public class Game implements GameStatus, GameWorld {
	
	private static final int INITIAL_SUNCOINS = 50;

    private boolean playerQuits;
    
    private int sunCoins;
    
    private long seed;
    
    private Level level;
    
    private int cycle;
    
    private GameObjectContainer container;
    
    private ZombiesManager zombiesManager;
    
    private SunsManager sunsManager;
    
	private Deque<GameAction> actions;
	
	private int score;
	
	private Record record;
	
	private boolean newRecord_game;
    
	public Game(long seed, Level level) throws GameException {
    	this.seed = seed;
    	this.level = level;
    	reset(level, seed);
	}
    
    public Level getLevel() {
    	return this.level;
    }
    
    public long getSeed() {
    	return this.seed;
    }
    
    public void reset() throws GameException {
    	reset(this.level, this.seed);
    }
    
    public void reset(Level level, long seed) throws GameException {
    	this.level = level;
    	this.seed = seed;
    	this.sunCoins = INITIAL_SUNCOINS;
    	this.playerQuits = false;
		Random num_random = new Random(this.seed);
		container = new GameObjectContainer();
		zombiesManager = new ZombiesManager(this, this.level, num_random);
		sunsManager = new SunsManager(this, num_random);
		this.cycle = 0;
		this.actions = new ArrayDeque<>();
		this.score = 0;
		this.record = Record.loadRecord(this.level);
		newRecord_game = false;
    }
	

	public boolean isPlayerQuits() {
		if (playerQuits) return true;
		else return false;
	}

	public boolean execute(Command command) throws GameException {
		return command.execute(this);
	}
	
	public boolean isPositionEmpty(int a, int b) {
		List<GameItem> list = getObjectInPosition(a, b);
		for (GameItem item : list) {
			if (item.filledPosition()) return false;
		}
		
		return true;
		
	}
	
	public String positionToString(int a, int b) {
		StringBuilder str = new StringBuilder();
		str.append(container.drawObject(a, b));
		return str.toString();
	}

	public void playerQuits(boolean exit) {
		playerQuits = exit;
	}


	public int getCycle() {
		return this.cycle;
	}
	

	public int getSuncoins() {
		return this.sunCoins;
	}
	
	public void tryToBuy(int cost) throws GameException{
		if (cost > getSuncoins()) {
			throw new NotEnoughCoinsException(Messages.NOT_ENOUGH_COINS);
		}
		comprar(cost);
	}
	
	public void checkValidPlantPosition(int col, int row) throws GameException{
		if (!isPositionEmpty(col, row)){
			throw new InvalidPositionException(String.format(Messages.INVALID_POSITION, col, row), col, row);
		}
	}
	
	public void checkValidZombiePosition(int col, int row) throws GameException{
		if (!isPositionEmpty(col, row)){
			throw new InvalidPositionException(String.format(Messages.INVALID_POSITION, col, row), col, row);
		}
	}

	
	public boolean anadirPlanta(Plant plant) throws GameException {
		checkValidPlantPosition(plant.getCol(), plant.getRow());
		addItem(plant);
		return true;
	}
	
	public void addItem(GameObject obj) {
		container.addGameObject(obj);
	}
	
	public boolean acabaJuego() {
		return ZombiesGana() || PlayerWins();
	}

	public boolean ZombiesGana() {
		return zombiesManager.ZombiesGana();
	}

	public boolean PlayerWins() {
		return zombiesManager.PlayerWins();
	}
	
	public int getRemainingZombies() {
		return zombiesManager.getRemainingZombies();
	}
	
	public int getCaughtSuns() {
		return sunsManager.getCatchedSuns();
	}
	
	public int getGeneratedSuns() {
		return sunsManager.getGeneratedSuns();
	}
	
	
	private void comprar(int coste) {
		this.sunCoins -= coste;
	}
	
	private void addCycle() {
		this.cycle++;
	}
	
	public void anadirSuncoins(int cantidad) {
		this.sunCoins += cantidad;
	}
	
	public List<GameItem> getObjectInPosition(int col, int row) {
		return container.getObjectInPosition(col, row);
	}
	
	public void zombieDied() {
		zombiesManager.zombieDied();
	}
	
	public boolean anadirZombie(Zombie z) throws GameException {
		return zombiesManager.anadirZombie(z);
	}
	
	public void haveZombiesWin() {
		zombiesManager.haveZombiesWin();
	}
	
	public List<GameItem> getLimits(int col, int row){
		return container.getLimits(col, row);
	}
	
	public boolean catchCoins(int col, int row) throws GameException {
		if (!container.catchCoins(col, row)) {
			throw new NotCatchablePositionException(String.format(Messages.INVALID_POSITION, col, row), col, row);
		}
		return true;
	}
	
	public void addSun() {
		sunsManager.addSun();
	}
	
	public void update() throws GameException {

		// 1. Execute pending actions
		executePendingActions();

		// 2. Execute game Actions
		zombiesManager.GameAction();
		

		// 3. Game object updates
		container.update(this);
		sunsManager.update();
		// TODO add your code here

		// 4. & 5. Remove dead and execute pending actions
		boolean deadRemoved = true;
		while (deadRemoved || areTherePendingActions()) {
			// 4. Remove dead
			deadRemoved = this.container.eliminarMuertos();

			// 5. execute pending actions
			executePendingActions();
		}

		addCycle();
		boolean newRecord = record.update(this.score);
		if (newRecord) newRecord_game = true;
		

		// 6. Notify commands that a new cycle started
		Command.newCycle();
		
		
		// 7. Update record
		if (newRecord_game) {
			if (acabaJuego()) {
				record.save();
			}
		}
		// TODO your code here
	}
	
	public void addAction(GameAction act) { 
		actions.add(act);	
	}

	private void executePendingActions() {
		while (!this.actions.isEmpty()) {
			GameAction action = this.actions.removeLast();
			action.execute(this);
		}
	}

	private boolean areTherePendingActions() {
		return this.actions.size() > 0;
	}	
	
	public void addPoints(int points) {
		this.score += points;
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getRecord() {
		return record.getRecord();
	}
	
	public String isNewRecord() {
		StringBuilder str = new StringBuilder();
		if (this.newRecord_game) {
			str.append(Messages.NEW_RECORD);
		}
		else {
			str.append(Messages.RECORD);
		}
		str.append(getRecord());
		
		return str.toString();
	}

}
