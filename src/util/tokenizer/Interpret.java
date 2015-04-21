package util.tokenizer;

import util.parser.ParserInput;

/**
 * An Interpret is meant to parse a part of an input if it doesn't match any
 * symbol.
 * 
 * @author Felix
 * @version 1.0
 *
 */
public interface Interpret {
	/**
	 * Tries to parse a part of an input if it doesn't match any symbol.
	 * 
	 * @param input
	 *            The input to parse a part from at its beginning.
	 * @return the result of the parsing of the part, null if this part should
	 *         be omitted.
	 * @throws NoInterpretationException
	 *             when the Interpret could not read any token.
	 * @throws UnexpectedTokenException
	 *             when the Interpret deems the input cannot be interpreted by
	 *             any further Interpret, thus ending the tokenization.
	 */
	public Object interpret(ParserInput input) throws NoInterpretationException, UnexpectedTokenException;
}
