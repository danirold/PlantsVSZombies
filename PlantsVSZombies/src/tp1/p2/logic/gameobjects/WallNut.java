package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class WallNut extends Plant{
	
	private static final int DANO = 0;
	private static final int COST = 50;
	private static final int VIDA_INICIAL = 10;
	
	
	public WallNut() {
		super();
	}
	
	public WallNut(int col, int row, GameWorld game) {
		super(col,row, game);
		this.vida = VIDA_INICIAL;
		this.cost = COST;
	}
	
	public String getName() {
		return Messages.WALLNUT_NAME;
	}
	
	public String getShortcut() {
		return Messages.WALLNUT_NAME_SHORTCUT;
	}
	
	public String getShortcut2() {
		return Messages.WALLNUT_SHORTCUT;
	}
	
	protected String getSymbol() {
		return Messages.WALLNUT_SYMBOL;
	}
	
	@Override
	public WallNut create (int col, int row, int ciclo_anadido, GameWorld game) {
    	WallNut wallnut = new WallNut(col, row, game);
    	return wallnut;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(getShortcut(), COST, DANO, VIDA_INICIAL);
    }
		
	public boolean update() {
		return true;
	}
}
