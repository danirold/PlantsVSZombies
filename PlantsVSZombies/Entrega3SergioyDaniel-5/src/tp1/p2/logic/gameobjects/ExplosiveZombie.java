package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class ExplosiveZombie extends Zombie {
	
	private static final int DANO = 1;
	private static final int DANO_EXPLOSION = 3;
	private static final int VIDA_INICIAL = 5;
	private static final int SPEED = 2;
	private static final int ZOMBIE_TYPE = 3;
	
	public ExplosiveZombie() {
		super();
	}
	
	public ExplosiveZombie(int col, int row, GameWorld game, int ciclo_anadido) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.dano = DANO;
		this.speed = SPEED;
		this.ciclo_anadido = ciclo_anadido;
	}
	
	public String getName() {
		return Messages.EXPLOSIVE_ZOMBIE_NAME;
	}
	
	protected String getSymbol() {
		return Messages.EXPLOSIVE_ZOMBIE_SYMBOL;
	}
	
	public int getZombieType() {
		return ZOMBIE_TYPE;
	}
	
	@Override
	public ExplosiveZombie create(int col, int row, int ciclo_anadido, GameWorld game) {
		ExplosiveZombie ez = new ExplosiveZombie(col, row, game, ciclo_anadido);
		return ez;
	}

	@Override
	public String getDescription() {
		return Messages.zombieDescription(getName(), SPEED, DANO, VIDA_INICIAL);
	}
	
	private void explosiveZombieAttack() {
		ExplosionAction exp = new ExplosionAction(this.col, this.row, DANO_EXPLOSION, false);
		game.addAction(exp);
	}
	
	public void onExit() {
		game.zombieDied();
		explosiveZombieAttack();
	}
}
