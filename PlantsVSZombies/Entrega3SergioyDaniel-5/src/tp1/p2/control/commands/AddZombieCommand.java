package tp1.p2.control.commands;

import tp1.p2.control.Command;
import tp1.p2.control.exceptions.CommandParseException;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.logic.GameWorld;
import tp1.p2.logic.gameobjects.Zombie;
import tp1.p2.logic.gameobjects.ZombieFactory;
import tp1.p2.view.Messages;

public class AddZombieCommand extends Command {
	
	private static final int COMMAND_ZOMBIE_LENGTH = 4;

	private int zombieIdx;

	private int col;

	private int row;

	public AddZombieCommand() {

	}

	private AddZombieCommand(int zombieIdx, int col, int row) {
		this.zombieIdx = zombieIdx;
		this.col = col;
		this.row = row;
	}

	@Override
	protected String getName() {
		return Messages.COMMAND_ADD_ZOMBIE_NAME;
	}

	@Override
	protected String getShortcut() {
		return Messages.COMMAND_ADD_ZOMBIE_SHORTCUT;
	}

	@Override
	public String getDetails() {
		return Messages.COMMAND_ADD_ZOMBIE_DETAILS;
	}

	@Override
	public String getHelp() {
		return Messages.COMMAND_ADD_ZOMBIE_HELP;
	}

	@Override
	public boolean execute(GameWorld game) throws GameException {
		Zombie z = ZombieFactory.SpawnZombie(this.zombieIdx, game, this.col, this.row);
		if (game.anadirZombie(z)) {
			game.update();
			return true;
		}
		else return false;
	}
	
	private void isValidZombie(int idx) throws GameException{
		if (!ZombieFactory.isValidZombie(idx)) throw new CommandParseException(Messages.INVALID_GAME_OBJECT);
	}

	@Override
	public Command create(String[] parameters) throws GameException {
		int i, j , idx;
		correctParametersLength(parameters.length, COMMAND_ZOMBIE_LENGTH);
		try {
			idx = Integer.parseInt(parameters[1]);
			i = Integer.parseInt(parameters[2]);
			j = Integer.parseInt(parameters[3]);
		}
		catch (NumberFormatException e) {
			throw new CommandParseException(Messages.INVALID_POSITION.formatted(parameters[2], parameters[3]), e);
		}
		isValidZombie(idx);
		isInBoard(i, j, Game.NUM_COLS + 1, Game.NUM_ROWS);
		Command command = new AddZombieCommand(idx, i, j);
		return command;
	}

}
