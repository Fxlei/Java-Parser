package util.parser;

import java.util.LinkedList;
import java.util.List;

/**
 * A Parser using {@link ProductionParser}s to parse input from start to end by
 * trying to match Productions.
 * 
 * @author Felix
 * @version 1.1
 *
 */
public class Parser {
	/**
	 * List of {@link ProductionParser}s sorted by priority.
	 */
	private final List<ProductionParser> prods;
	/**
	 * List of keys parallel to prods.
	 */
	private final List<String> keys;
	
	/**
	 * Creates a new Parser without any {@link ProductionParser}.
	 */
	public Parser() {
		prods = new LinkedList<ProductionParser>();
		keys = new LinkedList<String>();
	}
	
	/**
	 * Adds a new {@link ProductionParser} after all other
	 * {@link ProductionParser}s. Removes the key of any
	 * {@link ProductionParser} with the same key.
	 * 
	 * @param pp
	 *            The {@link ProductionParser} to append.
	 * @param key
	 *            The key for the new {@link ProductionParser}, has no key if
	 *            null.
	 */
	public void append(ProductionParser pp, String key) {
		insert(keys.size(), pp, key);
	}
	
	/**
	 * Adds a new {@link ProductionParser} after the {@link ProductionParser}
	 * with key pre. Removes the key of any {@link ProductionParser} with the
	 * same key.
	 * 
	 * @param pre
	 *            The key of the {@link ProductionParser} to put the new one
	 *            after.
	 * @param pp
	 *            The {@link ProductionParser} to add.
	 * @param key
	 *            The key for the new {@link ProductionParser}, has no key if
	 *            null.
	 */
	public void addAfter(String pre, ProductionParser pp, String key) {
		if (pre == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(pre) + 1, pp, key);
	}
	
	/**
	 * Adds a new {@link ProductionParser} before the {@link ProductionParser}
	 * with key pre. Removes the key of any {@link ProductionParser} with the
	 * same key.
	 * 
	 * @param post
	 *            The key of the {@link ProductionParser} to put the new one
	 *            before.
	 * @param pp
	 *            The {@link ProductionParser} to add.
	 * @param key
	 *            The key for the new {@link ProductionParser}, has no key if
	 *            null.
	 */
	public void addBefore(String post, ProductionParser pp, String key) {
		if (post == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(post), pp, key);
	}
	
	/**
	 * Inserts a {@link ProductionParser} at a given index. Removes the key of
	 * any {@link ProductionParser} with the same key.
	 * 
	 * @param index
	 *            The index to insert the new {@link ProductionParser} a.
	 * @param pp
	 *            The {@link ProductionParser} to add.
	 * @param key
	 *            The key for the new {@link ProductionParser}, has no key if
	 *            null.
	 */
	private void insert(int index, ProductionParser pp, String key) {
		if (pp == null) {
			throw new NullPointerException();
		} else if (index == -1) {
			throw new IllegalArgumentException("Relative ProductionParser does not exist.");
		}
		int original = keys.indexOf(key);
		if (original != -1) {
			keys.set(original, null);
		}
		keys.add(index, key);
		prods.add(index, pp);
	}
	
	/**
	 * Parses an input.
	 * 
	 * @param input
	 *            The input to parse.
	 * @return The Result of the parsing.
	 * @throws ParserException
	 *             If the input could not be parsed.
	 */
	public Object parse(ResetableParserInput input) throws ParserException {
		Object parsed = Production.Symbol.SOI;
		while (!input.isEmpty()) {
			parsePart(parsed, input);
		}
		return completeParsing(parsed);
	}
	
	/**
	 * Parses an string.
	 * 
	 * @param input
	 *            The input to parse.
	 * @return The Result of the parsing.
	 * @throws ParserException
	 *             If the input could not be parsed.
	 */
	public Object parse(String input) throws ParserException {
		return parse(new ResetableStringParserInput(input));
	}
	
	/**
	 * Parses one part of input, by testing all {@link ProductionParser}s until
	 * one succeeds.
	 * 
	 * @param parsed
	 *            The result of the parsing of the last part.
	 * @param input
	 *            The rest of the input.
	 * @return The result of the parsing of this part.
	 * @throws NoPossibleProductionException
	 *             If none of the {@link ProductionParser}s could match the
	 *             input.
	 */
	private Object parsePart(Object parsed, ResetableParserInput input) throws NoPossibleProductionException {
		for (ProductionParser prod : prods) {
			Object result = prod.tryParse(parsed, input);
			if (result == null) {
				input.reset();
			} else {
				input.confirm();
				return result;
			}
		}
		throw new NoPossibleProductionException(parsed);
	}
	
	/**
	 * Tries to complete the parsing on a parsing result and confirms its
	 * validity.
	 * 
	 * @param parsed
	 *            The result of the parsing to finalize.
	 * @return The completed result of the parsing.
	 * @throws ParserException
	 *             If the result is not complete, if more input is awaited.
	 */
	public Object completeParsing(Object parsed) throws ParserException {
		try {
			return parsePart(parsed, new EmptyParserInput());
		} catch (NoPossibleProductionException e) {
			throw new ParserException("Unexpected END_OF_INPUT");
		}
	}
}
