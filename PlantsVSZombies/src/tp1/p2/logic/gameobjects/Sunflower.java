package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sunflower extends Plant{
	
	private static final int DANO = 0;
	private static final int COST = 20;
	private static final int VIDA_INICIAL = 1;
	private static final int FREQUENCY = 3;
	
	public Sunflower() {
		super();
	}
	
	public Sunflower(int col, int row, int ciclo_anadido, GameWorld game) {
		super(col, row, game);
		this.vida = VIDA_INICIAL;
		this.ciclo_anadido = ciclo_anadido;
		this.cost = COST;
	}
	
	public String getName() {
		return Messages.SUNFLOWER_NAME;
	}
	
	public String getShortcut() {
		return Messages.SUNFLOWER_NAME_SHORTCUT;
	}
	
	public String getShortcut2() {
		return Messages.SUNFLOWER_SHORTCUT;
	}
	
	protected String getSymbol() {
		return Messages.SUNFLOWER_SYMBOL;
	}
	
	@Override
	public Sunflower create (int col, int row, int ciclo_anadido, GameWorld game) {
    	Sunflower sunflower = new Sunflower(col, row, ciclo_anadido, game);
    	return sunflower;
	}

	@Override
	public String getDescription() {
		return Messages.plantDescription(getShortcut(), COST, DANO, VIDA_INICIAL);
    }
	
	private boolean modulo() {
		return (game.getCycle() - this.ciclo_anadido) % FREQUENCY == 0 && (game.getCycle() - this.ciclo_anadido) != 0;
	}
		
	@Override
	public boolean update() {
		if (modulo()) return false;
		else return true;
	}

}
