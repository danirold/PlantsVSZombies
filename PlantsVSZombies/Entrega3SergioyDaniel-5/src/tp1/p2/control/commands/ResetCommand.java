package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.Level;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;

public class ResetCommand extends Command {
	
	private static final int COMMAND_RESET_LENGTH1 = 1;
	private static final int COMMAND_RESET_LENGTH2 = 3;

	private Level level;

	private long seed;
	

	public ResetCommand() {
		
		
	}

	public ResetCommand(Level level, long seed) {
		this.level = level;
		this.seed = seed;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_RESET_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_RESET_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_RESET_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_RESET_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		if (level != null) {
			game.reset(this.level, this.seed);
			System.out.println(String.format(Messages.CONFIGURED_LEVEL, game.getLevel()));
			System.out.println(String.format(Messages.CONFIGURED_SEED,game.getSeed()));
			newCycle();

		}
		else {
			game.reset();
			System.out.println(String.format(Messages.CONFIGURED_LEVEL, game.getLevel()));
			System.out.println(String.format(Messages.CONFIGURED_SEED,game.getSeed()));
			newCycle();

		}
		
		return true;
	}
	
	public void correctParametersLength(int length, int correct_length1, int correct_length2) throws GameException{
		if (length != correct_length1 && length != correct_length2) throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	}
	
	private void correctLevel(String level) throws GameException {
		if (Level.valueOfIgnoreCase(level) == null) throw new CommandParseException(Messages.INVALID_COMMAND);
	}
	
	

	@Override
	public Command create(String[] parameters) throws GameException {
		correctParametersLength(parameters.length, COMMAND_RESET_LENGTH1, COMMAND_RESET_LENGTH2);
		if (parameters.length == 1) {
			try {
				Command command = new ResetCommand();
				return command;
			}
			catch (NumberFormatException e) {
				throw new CommandParseException(Messages.SEED_NOT_A_NUMBER, e);
			}	
		}
		else {
			correctLevel(parameters[1]);
			try {
				Command command = new ResetCommand(Level.valueOfIgnoreCase(parameters[1]), Integer.parseInt(parameters[2]));
				return command;
			}
			catch (NumberFormatException e) {
				throw new CommandParseException(Messages.SEED_NOT_A_NUMBER, e);
			}
			
		}
		
	}

}
