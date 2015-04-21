package util.parser;

/**
 * Represents an Exception while parsing, thrown because no production matched
 * the input.
 * 
 * @author Felix
 * @version 1.0
 */
public class NoPossibleProductionException extends ParserException {
	/**
	 * The result of the parsing until the exception was thrown.
	 */
	private Object parsed;
	
	/**
	 * Creates a new {@link NoPossibleProductionException} with some infrmation
	 * about the parsing until then.
	 * 
	 * @param parsed
	 *            The result from the parsing of the last part.
	 */
	public NoPossibleProductionException(Object parsed) {
		this.parsed = parsed;
	}
	
	/**
	 * Gets the result from the parsing of the last part, before this exception was thrown.
	 * @return The result from the parsing of the last part, before this exception was thrown.
	 */
	public Object getParsed() {
		return parsed;
	}
}
