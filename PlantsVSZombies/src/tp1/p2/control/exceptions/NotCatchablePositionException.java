package tp1.p2.control.exceptions;

public class NotCatchablePositionException extends InvalidPositionException {
	private static final long serialVersionUID = 1L;
	
	public NotCatchablePositionException() {
		super();
	}

	public NotCatchablePositionException(String message, int col, int row) {
		super(message, col, row);
	}

	public NotCatchablePositionException(Throwable cause) {
		super(cause);
	}

	public NotCatchablePositionException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public NotCatchablePositionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
