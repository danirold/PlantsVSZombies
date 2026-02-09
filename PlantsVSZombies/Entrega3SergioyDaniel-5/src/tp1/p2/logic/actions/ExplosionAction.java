package tp1.p2.logic.actions;

import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;

public class ExplosionAction implements GameAction {
	
	private static final int POINTS = 10;

	private int col;

	private int row;

	private int damage;
	
	private boolean plant;

	public ExplosionAction(int col, int row, int damage, boolean plant) {
		this.col = col;
		this.row = row;
		this.damage = damage;
		this.plant = plant;
	}

	@Override
	public void execute(GameWorld game) {
		if (!plant) {
			for (GameItem item : game.getLimits(this.col, this.row)) {
				item.receiveZombieAttack(damage);	
			}	
		}
		else {
			for (GameItem item : game.getLimits(this.col, this.row)) {
				item.receivePlantAttack(damage);
				if (!item.isAlive()) game.addPoints(POINTS);//se suman 10 puntos extra si es explosión
			}
		}
	}
}
