package tp1.p2.logic;

import tp1.p2.control.Level;

public interface GameStatus {

	public int getCycle();

	public int getSuncoins();
	
	public Level getLevel();
	
	public long getSeed();
	
	public String positionToString(int a, int b);
	
	public int getRemainingZombies();
	
	public boolean ZombiesGana();
	
	public boolean PlayerWins();
	
	public boolean isPlayerQuits();
	
    public int getCaughtSuns();
	
	public int getGeneratedSuns();
	
	public int getScore();

	public String isNewRecord();

}
