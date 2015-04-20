package util.parser;

/**
 * Extends {@link ParserInput} with the functionality to reset input after an
 * attempt to parse a part of the input failed.
 * 
 * @author Felix
 * @version 1.0
 */
public interface ResetableParserInput extends ParserInput {
	/**
	 * Resets the input to the last confirmed state, or the first state, if it
	 * has never been confirmed.
	 */
	void reset();
	
	/**
	 * Confirms the actual state of the input.
	 */
	void confirm();
}
