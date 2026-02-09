package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.view.Messages;

public class AddPlantCheatCommand extends AddPlantCommand implements Cloneable {
	
	private static final int COMMAND_CHEAT_PLANT_LENGTH = 4;


	public AddPlantCheatCommand() {
		
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CHEAT_PLANT_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CHEAT_PLANT_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CHEAT_PLANT_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CHEAT_PLANT_HELP;
	}
	
	public Command create(String[] parameters) throws GameException {
		int i, j;
		correctParametersLength(parameters.length, COMMAND_CHEAT_PLANT_LENGTH);
		isValidPlant(parameters[1]);
		try {
			i = Integer.parseInt(parameters[2]);
			j = Integer.parseInt(parameters[3]);
		}
		catch (NumberFormatException e) {
			throw new CommandParseException(String.format(Messages.INVALID_POSITION, parameters[2], parameters[3]), e);
		}
		isInBoard(i, j, Game.NUM_COLS, Game.NUM_ROWS);
		Command command = new AddPlantCommand(false, i, j, parameters[1]);
		
		return command;
	}

}
