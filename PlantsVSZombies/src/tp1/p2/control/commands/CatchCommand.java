package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class CatchCommand extends Command {
	
	private static final int COMMAND_CATCH_LENGTH = 3;

	private static boolean caughtSunThisCycle = false;

	private int col;

	private int row;

	public CatchCommand() {
		caughtSunThisCycle = false;
	}
	
	@Override
	protected void newCycleStarted() {
		caughtSunThisCycle = false;
	}

	private CatchCommand(int col, int row) {
		this.col = col;
		this.row = row;
		caughtSunThisCycle = false;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_CATCH_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_CATCH_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_CATCH_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_CATCH_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if (game.catchCoins(this.col, this.row)) {
			caughtSunThisCycle = true;
			return true;
		}
		else return false;
		
	}
	
	private void sunAlreadyCaught() throws GameException {
		if (caughtSunThisCycle) throw new CommandParseException(Messages.SUN_ALREADY_CAUGHT);
	}


	@Override
	public Command create(String[] parameters) throws GameException {
		int i, j;
		sunAlreadyCaught();
		try {
			i = Integer.parseInt(parameters[1]);
			j = Integer.parseInt(parameters[2]);
		}
		catch (NumberFormatException e) {
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[1], parameters[2]), e);
		}
		correctParametersLength(parameters.length, COMMAND_CATCH_LENGTH);
		isInBoard(i, j, Game.NUM_COLS, Game.NUM_ROWS);
		Command command = new CatchCommand(i, j);
		return command;	
	}

}
