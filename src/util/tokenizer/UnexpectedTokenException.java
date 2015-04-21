package util.tokenizer;

import util.parser.ParserException;

/**
 * Exception thrown when a Tokenizer could not read a token.
 * 
 * @author Felix
 * @version 1.0
 *
 */
public class UnexpectedTokenException extends ParserException {

	public UnexpectedTokenException() {
		// TODO Auto-generated constructor stub
	}

	public UnexpectedTokenException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedTokenException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedTokenException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public UnexpectedTokenException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}
	
}
