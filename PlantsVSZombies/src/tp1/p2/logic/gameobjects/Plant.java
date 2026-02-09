package tp1.p2.logic.gameobjects;
import tp1.p2.logic.GameWorld;


public abstract class Plant extends GameObject {
	
	protected int cost;
	
	public Plant() {
		super();
	}
	
	public Plant(int col, int row, GameWorld game) {
		super(col, row, game);
	}
	
	public abstract String getName();
	
	public abstract String getShortcut();
	
	public abstract String getShortcut2();
	
	public abstract String getDescription();
	
	public int getCost() {
		return this.cost;
	}
	
	protected void cherryBombAttack() {
		
	}
	
	public void onEnter() {
		
	}
	
	public void onExit() {
		
	}
	
	public abstract boolean update();
	
	public abstract Plant create(int col, int row, int ciclo_anadido, GameWorld game);
	
	public boolean matchPlant(String plant) {
		String shortcut = getShortcut2();
		String name = getName();
		return shortcut.equalsIgnoreCase(plant) || name.equalsIgnoreCase(plant);
	}
	
	public boolean receivePlantAttack(int damage) {
		return false;
	}
	
	public boolean receiveZombieAttack(int damage) {
		this.vida -= damage;
		return true;
	}
}
