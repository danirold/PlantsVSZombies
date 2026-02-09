package tp1.p2.logic.gameobjects;

import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class Sun extends GameObject {
	private int MAX_CICLOS = 10;
	private static int GENERATED_SUNS;
	private static int CAUGHT_SUNS;
	private static final int CANTIDAD = 10;

	
	public Sun(GameWorld game, int col, int row) {
		super(col, row, game);
		this.vida = MAX_CICLOS;
	}
	
	public void onEnter() {
		GENERATED_SUNS++;
	}


	public void onExit() {
		CAUGHT_SUNS++;
	}
	
	public static int getGeneratedSuns() {
		return GENERATED_SUNS;
	}
	
	public static int getCaughtSuns() {
		return CAUGHT_SUNS;
	}
	
	public static void resetSuns() {
		GENERATED_SUNS = 0;
		CAUGHT_SUNS = 0;
	}

	@Override
	protected String getSymbol() {
		return Messages.SUN_SYMBOL;
	}

	@Override
	public String getDescription() {
		return Messages.SUN_DESCRIPTION;
	}
	
	public String getStatus() {
		if (isAlive()) return Messages.status(getSymbol(), this.vida);
		else return "";
	}

	@Override
	public boolean update() {
		this.vida--;
		return true;
	}

	@Override
	public boolean receiveZombieAttack(int damage) {
		return false;
	}
	
	public boolean receivePlantAttack(int damage) {
		return false;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Sun create(int col, int row, int ciclo_anadido, GameWorld game) {
		Sun s = new Sun(game, col, row);
		return s;
	}
	
    public boolean catchSun() {
    	this.vida = 0;	
    	onExit();
    	game.anadirSuncoins(CANTIDAD);
    	return true;
	}
    
    public boolean filledPosition() {
		return false;
	}
}
