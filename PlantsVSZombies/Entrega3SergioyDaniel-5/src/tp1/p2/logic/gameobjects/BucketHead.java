package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class BucketHead extends Zombie {
	
	private static final int DANO = 1;
	private static final int VIDA_INICIAL = 8;
	private static final int SPEED = 4;
	private static final int ZOMBIE_TYPE = 1;
	
	public BucketHead() {
		super();
	}
	
	public BucketHead(int col, int row, GameWorld game, int ciclo_anadido) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.dano = DANO;
		this.speed = SPEED;
		this.ciclo_anadido = ciclo_anadido;
	}
	
	
	public String getName() {
		return Messages.BUCKETHEAD_NAME;
	}
	
	protected String getSymbol() {
		return Messages.BUCKETHEAD_SYMBOL;
	}
	
	public int getZombieType() {
		return ZOMBIE_TYPE;
	}
	
	@Override
	public BucketHead create(int col, int row, int ciclo_anadido, GameWorld game) {
		BucketHead BH = new BucketHead(col, row, game, ciclo_anadido);
		return BH;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(getName(), SPEED, DANO, VIDA_INICIAL);
	}	
}
