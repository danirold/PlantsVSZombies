package tp1.p2.control;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import tp1.p2.control.commands.AddPlantCheatCommand;
import tp1.p2.control.commands.AddPlantCommand;
import tp1.p2.control.commands.AddZombieCommand;
import tp1.p2.control.commands.CatchCommand;
import tp1.p2.control.commands.ExitCommand;
import tp1.p2.control.commands.HelpCommand;
import tp1.p2.control.commands.ListPlantsCommand;
import tp1.p2.control.commands.ListZombiesCommand;
import tp1.p2.control.commands.NoneCommand;
import tp1.p2.control.commands.ResetCommand;
import tp1.p2.control.commands.ShowRecordCommand;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.control.exceptions.InvalidPositionException;
import tp1.p2.logic.GameWorld;
import tp1.p2.view.Messages;


/**
 * Represents a user action in the game.
 *
 */
public abstract class Command {

	/* @formatter:off */
	private static final List<Command> AVAILABLE_COMMANDS = Arrays.asList(
			new AddPlantCommand(),
			new ListPlantsCommand(),
			new ResetCommand(),
			new HelpCommand(),
			new ExitCommand(),
			new NoneCommand(),
			new ListZombiesCommand(),
			new AddZombieCommand(),
			new AddPlantCheatCommand(),
			new CatchCommand(),
			new ShowRecordCommand()
		);
	/* @formatter:on */

	private static Command defaultCommand = new NoneCommand();

	public static Command parse(String[] commandWords) throws GameException {
		
		if (commandWords.length == 1 && commandWords[0].isEmpty()) {
			return defaultCommand;
		}
		for (Command command : AVAILABLE_COMMANDS) {
			if (command.matchCommand(commandWords[0])) {
				return command.create(commandWords);
			}
		}
		
		throw new CommandParseException(Messages.UNKNOWN_COMMAND);
	}

	public static Iterable<Command> getAvailableCommands() {
		return Collections.unmodifiableList(AVAILABLE_COMMANDS);
	}

	abstract protected String getName();

	abstract protected String getShortcut();

	abstract public String getDetails();

	abstract public String getHelp();
	
	public static void newCycle() {
		for(Command c : AVAILABLE_COMMANDS) {
			c.newCycleStarted();
		}
	}

	public boolean isDefaultAction() {
		return Command.defaultCommand == this;
	}

	public boolean matchCommand(String token) {
		String shortcut = getShortcut();
		String name = getName();
		return shortcut.equalsIgnoreCase(token) || name.equalsIgnoreCase(token)
				|| (isDefaultAction() && "".equals(token));
	}

	
	 /* @throws GameException if there is an error executing the command.
	 */
	public abstract boolean execute(GameWorld game) throws GameException;

	public Command create(String[] parameters) throws GameException{
		if (parameters.length != 1) {
			throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
		}
		return this;
	}
		
	
	
	protected void newCycleStarted() {
	}
	
	public void correctParametersLength(int length, int correct_length) throws GameException{
		if (length < correct_length) {
			throw new CommandParseException(Messages.COMMAND_PARAMETERS_MISSING);
		}
		else if (length > correct_length) throw new CommandParseException(Messages.COMMAND_INCORRECT_PARAMETER_NUMBER);
	}
	
	public void isInBoard(int col, int row, int col_limit, int row_limit) throws GameException {
		if (!(col < col_limit && row < row_limit)) throw new InvalidPositionException(String.format(Messages.INVALID_POSITION, col, row), col, row);
	}

}