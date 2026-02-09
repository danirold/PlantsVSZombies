package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

/**
 * Base class for game non playable character in the game.
 *
 */
public abstract class GameObject implements GameItem {

	protected GameWorld game;

	protected int col;

	protected int row;
	
	protected int vida;
	
	protected int ciclo_anadido;
	
	GameObject(){
		
	}


	GameObject(int col, int row, GameWorld game) { 
		this.game = game;
		this.col = col;
		this.row = row;
	}

	public boolean isInPosition(int col, int row) {
		return this.col == col && this.row == row;
	}

	public int getCol() {
		return col;
	}

	public int getRow() {
		return row;
	}
	
	public boolean isAlive() {
		if (this.vida > 0) return true;
		else return false;
	}

	public String toString() {
		if (isAlive()) {
			return this.getStatus();
		} 
		else {
			return "";
		}
	}
	
	private String getStatus() {
		return Messages.status(getSymbol(), this.vida);
	}

	abstract protected String getSymbol();

	abstract public String getDescription();

	abstract public boolean update();
	
	abstract public void onEnter();
	
	abstract public void onExit();
	
	abstract public boolean receiveZombieAttack(int damage);
	
	abstract public boolean receivePlantAttack(int damage);
	
	abstract public String getName();
	
	public abstract GameObject create(int col, int row, int ciclo_anadido, GameWorld game);
	
	public boolean catchSun() {
		return false;
	}
	
	public boolean filledPosition() {
		return true;
	}
}
