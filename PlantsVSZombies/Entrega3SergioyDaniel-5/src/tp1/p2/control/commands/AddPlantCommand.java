package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Plant;
import tp1.p2.logic.gameobjects.PlantFactory;
import tp1.p2.view.Messages;

public class AddPlantCommand extends Command implements Cloneable {
	
	private static final int COMMAND_PLANT_LENGTH = 4;

	protected int col;

	protected int row;

	protected String plantName;

	protected boolean consumeCoins;

	public AddPlantCommand() {
		
	}

	public AddPlantCommand(boolean consumeCoins, int col, int row, String plantName) {
		this.col = col;
		this.row = row;
		this.plantName = plantName;
		this.consumeCoins = consumeCoins;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_DETAILS; 
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		Plant plant = PlantFactory.spawnPlant(this.plantName, game, this.col, this.row);
		if (consumeCoins) {
			game.tryToBuy(plant.getCost());
		}
		if (game.anadirPlanta(plant)) {
			game.update();
			return true;
		}
		
		else return false;
	}
	
	
	protected void isValidPlant(String parameter) throws GameException {
		if (!PlantFactory.isValidPlant(parameter)) throw new CommandParseException(Messages.INVALID_GAME_OBJECT);
	}
	
	@Override
	public Command create(String[] parameters) throws GameException {
		int i, j;
		correctParametersLength(parameters.length, COMMAND_PLANT_LENGTH);
		isValidPlant(parameters[1]);
		try {
			i = Integer.parseInt(parameters[2]);
			j = Integer.parseInt(parameters[3]);
		}
		catch (NumberFormatException e) {
			throw new CommandParseException(String.format(Messages.INVALID_POSITION, parameters[2], parameters[3]), e);
		}
		isInBoard(i, j, Game.NUM_COLS, Game.NUM_ROWS);
		Command command = new AddPlantCommand(true, i, j, parameters[1]);
		
		return command;
	}

}
