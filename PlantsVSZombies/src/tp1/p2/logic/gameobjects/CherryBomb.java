package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.logic.actions.ExplosionAction;
import tp1.p2.view.Messages;

public class CherryBomb extends Plant {
	
	private static final int DANO = 10;
	private static final int COST = 50;
	private static final int VIDA_INICIAL = 2;


	public CherryBomb() {
		super();
	}
	
	public CherryBomb(int col, int row, int ciclo_anadido, GameWorld game) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.ciclo_anadido = ciclo_anadido;
		this.cost = COST;
	}
	
	public String getName() {
		return Messages.CHERRYBOMB_NAME;
	}
	
	public String getShortcut() {
		return Messages.CHERRYBOMB_NAME_SHORTCUT;
	}
	
	public String getShortcut2() {
		return Messages.CHERRYBOMB_SHORTCUT;
	}
	
	protected String getSymbol() {
		if (game.getCycle() - this.ciclo_anadido == 1) return Messages.CHERRYBOMB_SYMBOL_1;
		else return Messages.CHERRYBOMB_SYMBOL_2;
	}
	
	@Override
	public CherryBomb create (int col, int row, int ciclo_anadido, GameWorld game) {
    	CherryBomb cherrybomb = new CherryBomb(col, row, ciclo_anadido, game);
    	return cherrybomb;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(getShortcut(), COST, DANO, VIDA_INICIAL);
    }
	
	@Override
	public boolean update() {
		cherryBombAttack();
		return true;
	}
	
	protected void cherryBombAttack() {
		if (game.getCycle() - this.ciclo_anadido == 2) {
			ExplosionAction exp = new ExplosionAction(this.col, this.row, DANO, true);
			game.addAction(exp);
			this.vida = 0;
		}	
	}
	
}




