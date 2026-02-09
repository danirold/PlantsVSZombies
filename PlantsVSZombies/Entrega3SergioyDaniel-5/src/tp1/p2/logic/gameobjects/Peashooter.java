package tp1.p2.logic.gameobjects;

import java.util.List;

import tp1.p2.logic.Game;
import tp1.p2.logic.GameItem;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Peashooter extends Plant {
	
	private static final int DANO = 1;
	private static final int COST = 50;
	private static final int VIDA_INICIAL = 3;

	public Peashooter() {
		super();
	}
	
	public Peashooter(int col, int row, GameWorld game) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.cost = COST;
	}
	
	public String getName() {
		return Messages.PEASHOOTER_NAME;
	}
	
	public String getShortcut() {
		return Messages.PEASHOOTER_NAME_SHORTCUT;
	}
	
	public String getShortcut2() {
		return Messages.PEASHOOTER_SHORTCUT;
	}
	
	protected String getSymbol() {
		return Messages.PEASHOOTER_SYMBOL;
	}
	
	@Override
    public Peashooter create(int col, int row, int ciclo_anadido, GameWorld game) {
    	Peashooter peashooter = new Peashooter(col, row, game);
    	return peashooter;
	}
    
	@Override
    public String getDescription() {
		return Messages.plantDescription(getShortcut(), COST, DANO, VIDA_INICIAL);
    }

	public boolean update() {
		peashooterAttack();
		return true;
	}
	
	private void peashooterAttack() { 
		boolean parar = false;
	    int i = this.col + 1;
	    
	    while (i < Game.NUM_COLS && !parar) {
	    	List<GameItem> items = game.getObjectInPosition(i, this.row);
	    	for (GameItem item : items) {
	    		if (item.receivePlantAttack(DANO)) parar = true;
	    	}
	    	++i;
	    }  
	}

}
