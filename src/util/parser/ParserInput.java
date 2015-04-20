package util.parser;


/**
 * {@link ParserInput} represents the input for a Parser and allows to read one
 * or more characters from the start of the input and potentially discard them.
 * <p>
 * Any implementation of Parser should manage its input, so that discarded input
 * gets available again, if an attempt to parse a part failed.
 * </p>
 * 
 * @author Felix
 * @version 1.1
 *
 */
public interface ParserInput {
	/**
	 * Reads the first char without discarding it. This does not advance the
	 * cursor. Consecutive calls to this {@link ParserInput#peek()} should
	 * return the same char.
	 * 
	 * @return the next char from the input.
	 * @throws EmptyParserInputException
	 *             If the input is empty.
	 */
	char peek() throws EmptyParserInputException;
	
	/**
	 * Reads the first char without discarding it. This does not advance the
	 * cursor. Consecutive calls to this {@link ParserInput#peek()} should
	 * return the same char.
	 * 
	 * @param n
	 *            number of chars to read.
	 * @return the next n chars from the input in a String, might be shorter if
	 *         the end of the input is reached.
	 */
	String peek(int n);
	
	/**
	 * Advances the inputs cursor by 1 characters.
	 */
	void advance();
	
	/**
	 * Advances the inputs cursor by n characters.
	 * 
	 * @param n
	 *            number of characters to advance by.
	 */
	void advance(int n);
	
	/**
	 * Tests if any further characters are available in the input.
	 * 
	 * @return true if there are no further characters available.
	 */
	boolean isEmpty();
}
