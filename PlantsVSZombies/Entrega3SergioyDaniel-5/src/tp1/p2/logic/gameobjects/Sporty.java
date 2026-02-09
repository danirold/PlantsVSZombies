package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sporty extends Zombie{
	
	private static final int DANO = 1;
	private static final int VIDA_INICIAL = 2;
	private static final int SPEED = 1;
	private static final int ZOMBIE_TYPE = 2;
	
	public Sporty() {
		super();
	}
	
	public Sporty(int col, int row, GameWorld game, int ciclo_anadido) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.dano = DANO;
		this.speed = SPEED;
		this.ciclo_anadido = ciclo_anadido;
	}
	
	public String getName() {
		return Messages.SPORTY_NAME;
	}
	
	protected String getSymbol() {
		return Messages.SPORTY_SYMBOL;
	}
	
	public int getZombieType() {
		return ZOMBIE_TYPE;
	}
	
	@Override
	public Sporty create(int col, int row, int ciclo_anadido, GameWorld game) {
		Sporty s = new Sporty(col, row, game, ciclo_anadido);
		return s;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(getName(), SPEED, DANO, VIDA_INICIAL);
	}
}


