package util.parser;

/**
 * A {@link ProductionParser} represents a process of a Parser, to try parse a
 * part of an input, depending on the result of the parsing of the last part.
 * 
 * @author Felix
 * @version 1.0
 *
 */
public interface ProductionParser {
	/**
	 * Tries parsing the next part of input.
	 * 
	 * @param o
	 *            The result of the parsing of the last part.
	 * @param input
	 *            The input trimmed of all parts that have already been parsed.
	 * @return the result of the parsing, null if the attempt to parse failed.
	 */
	public Object tryParse(Object o, ParserInput input);
}
