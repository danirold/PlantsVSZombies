package tp1.p2.control;

import java.io.IOException;
import java.util.Scanner;

import static tp1.p2.view.Messages.error;
import tp1.p2.control.exceptions.GameException;
import tp1.p2.logic.Game;
import tp1.p2.view.GamePrinter;
import tp1.p2.view.Messages;

/**
 * Accepts user input and coordinates the game execution logic.
 *
 */
public class Controller {

	private Game game;

	private Scanner scanner;

	private GamePrinter gamePrinter;
	

	public Controller(Game game, Scanner scanner) {
		this.game = game;
		this.scanner = scanner;
		this.gamePrinter = new GamePrinter(game);
	}

	/**
	 * Draw / Paint the game.
	 */
	private void printGame() {
		System.out.println(gamePrinter.toString());
	}

	/**
	 * Prints the final message once the match is finished.
	 * @throws IOException 
	 */
	public void printEndMessage() throws IOException {
		System.out.print(gamePrinter.endMessage());
	}

	/**
	 * Show prompt and request command.
	 *
	 * @return the player command as words
	 */
	private String[] prompt() {
		System.out.print(Messages.PROMPT);
		String line = scanner.nextLine();
		String[] words = line.toLowerCase().trim().split("\\s+");
		System.out.println(Messages.debug(line));

		return words;
	}
	
	
	/**
	 * Runs the game logic.
	 * @throws IOException 
	 */
	
	public void run() throws IOException {
		boolean refreshDisplay = true;

		while (!game.acabaJuego() && !game.isPlayerQuits()) {

			// 1. Draw
			if (refreshDisplay) {
				printGame();
			}

			
			String[] words = prompt();

		
			try {
				refreshDisplay = false;
				// 2-4. User action & Game Action & Update
				Command command = Command.parse(words);
				refreshDisplay = game.execute(command);
			} catch (GameException e) {
				System.out.println(error(e.getMessage()));
			}
		}
	

		if (refreshDisplay && !game.acabaJuego() && !game.isPlayerQuits()) {
			printGame();
		}
		
		if (game.acabaJuego()) {
			printGame();
			printEndMessage();
		}
		if (game.isPlayerQuits()) printEndMessage();
		
		
		
	}

}
