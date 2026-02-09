package tp1.p2.logic.gameobjects;

import java.util.List;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public abstract class Zombie extends GameObject{
	
	protected int speed;
	protected int dano;
	
	private static final int POINTS = 10;
	
	public Zombie () {
		super();
	}
	
	public Zombie(int col, int row, GameWorld game) {
		super(col, row, game);
	}
	
	public abstract int getZombieType();
	
	public boolean matchZombie(int ZombieType) {
		return getZombieType() == ZombieType;
	}
	
	public abstract String getName();

	protected abstract String getSymbol();

	public abstract String getDescription();
    

	public abstract Zombie create(int col, int row, int ciclo_anadido, GameWorld game);
	
	@Override
	public boolean update() {
		ZombieWalk();
		ZombieAttack();
		if (zombieWin()) game.haveZombiesWin();
		return true;
	}

	@Override
	public void onEnter() {
		
		
	}

	public void onExit() {
		game.zombieDied();
	}
	
	public boolean receiveZombieAttack(int damage) {
		return false;
	}
	
	public boolean receivePlantAttack(int damage) {
		if (this.isAlive()) {
			this.vida -= damage;
			if (!this.isAlive()) {
				onExit();
				game.addPoints(POINTS);//sumamos siempre 10 puntos por cualquier muerte
			}
			return true;
		}
		else return false;
	}
	
	
	private void ZombieAttack() {
		List<GameItem> others = game.getObjectInPosition(col - 1, row);
		
		for (GameItem item : others) {
			item.receiveZombieAttack(this.dano);
		}
	}
	
	private void ZombieWalk() {
		canWalk();
	}
	
	private boolean zombieWin() {
		return this.col < 0;
	}

    private void canWalk() {
    	if ((game.getCycle() - this.ciclo_anadido) % this.speed == 0 && (game.getCycle() - this.ciclo_anadido) != 0) {
			if (game.isPositionEmpty(this.col - 1, this.row)) this.col--;
		}
	}
}
