package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ZombieComun extends Zombie {
	
	private static final int DANO = 1;
	private static final int VIDA_INICIAL = 5;
	private static final int SPEED = 2;
	private static final int ZOMBIE_TYPE = 0;
	
	public ZombieComun() {
		super();
	}
	
	public ZombieComun(int col, int row, int ciclo_anadido, GameWorld game) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.dano = DANO;
		this.speed = SPEED;
		this.ciclo_anadido = ciclo_anadido;
	}
	
	
	public String getName() {
		return Messages.ZOMBIE_COMUN_NAME;
	}
	
	protected String getSymbol() {
		return Messages.ZOMBIE_COMUN_SYMBOL;
	}
	
	public int getZombieType() {
		return ZOMBIE_TYPE;
	}
	
	@Override
	public ZombieComun create(int col, int row, int ciclo_anadido, GameWorld game) {
		ZombieComun z = new ZombieComun(col, row, ciclo_anadido, game);
		return z;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(getName(), SPEED, DANO, VIDA_INICIAL);
	}
}
