package tp1.p2.control.exceptions;

public class InvalidPositionException extends GameException {

	private static final long serialVersionUID = 1L;
	protected int col;
	protected int row;
	
	public InvalidPositionException() {
		super();
	}

	public InvalidPositionException(String message, int col, int row) {
		super(message);
		this.col = col;
		this.row = row;
	}

	public InvalidPositionException(Throwable cause) {
		super(cause);
	}

	public InvalidPositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public InvalidPositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
