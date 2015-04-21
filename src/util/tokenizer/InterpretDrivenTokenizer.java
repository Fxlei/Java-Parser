package util.tokenizer;

import java.util.LinkedList;
import java.util.List;

import util.parser.ParserException;
import util.parser.ProductionParser;
import util.parser.ResetableParserInput;

public class InterpretDrivenTokenizer {
	/**
	 * List of {@link Interpret}s sorted by priority.
	 */
	private final List<Interpret> interprets;
	
	/**
	 * List of keys parallel to interprets.
	 */
	private final List<String> keys;
	
	/**
	 * Creates a new InterpretDrivenTokenizer without any {@link Interpret}.
	 */
	public InterpretDrivenTokenizer() {
		interprets = new LinkedList<Interpret>();
		keys = new LinkedList<String>();
	}
	
	/**
	 * Adds a new {@link Interpret} after all other {@link Interpret}s. Removes
	 * the key of any {@link Interpret} with the same key.
	 * 
	 * @param interpret
	 *            The {@link Interpret} to append.
	 * @param key
	 *            The key for the new {@link ProductionParser}, has no key if
	 *            null.
	 */
	public void append(Interpret interpret, String key) {
		insert(keys.size(), interpret, key);
	}
	
	/**
	 * Adds a new {@link Interpret} after the {@link Interpret} with key pre.
	 * Removes the key of any {@link Interpret} with the same key.
	 * 
	 * @param pre
	 *            The key of the {@link Interpret} to put the new one after.
	 * @param pp
	 *            The {@link Interpret} to add.
	 * @param key
	 *            The key for the new {@link Interpret}, has no key if null.
	 */
	public void addAfter(String pre, Interpret interpret, String key) {
		if (pre == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(pre) + 1, interpret, key);
	}
	
	/**
	 * Adds a new {@link Interpret} before the {@link Interpret} with key pre.
	 * Removes the key of any {@link Interpret} with the same key.
	 * 
	 * @param post
	 *            The key of the {@link Interpret} to put the new one before.
	 * @param pp
	 *            The {@link Interpret} to add.
	 * @param key
	 *            The key for the new {@link Interpret}, has no key if null.
	 */
	public void addBefore(String post, Interpret interpret, String key) {
		if (post == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(post), interpret, key);
	}
	
	/**
	 * Inserts an {@link Interpret} at a given index. Removes the key of any
	 * {@link Interpret} with the same key.
	 * 
	 * @param index
	 *            The index to insert the new {@link Interpret} at.
	 * @param interpret
	 *            The {@link Interpret} to add.
	 * @param key
	 *            The key for the new {@link Interpret}, has no key if null.
	 */
	private void insert(int index, Interpret interpret, String key) {
		if (interpret == null) {
			throw new NullPointerException();
		} else if (index == -1) {
			throw new IllegalArgumentException("Relative Interpret does not exist.");
		}
		int original = keys.indexOf(key);
		if (original != -1) {
			keys.set(original, null);
		}
		keys.add(index, key);
		interprets.add(index, interpret);
	}
	
	/**
	 * Tokenizes an input.
	 * 
	 * @param input
	 *            The input to tokenize.
	 * @return The Result of the tokenization.
	 * @throws ParserException
	 *             If the input could not be tokenized.
	 */
	public List<Object> tokenize(ResetableParserInput input) {
		List<Object> tokens = new LinkedList<Object>();
		while (!input.isEmpty()) {
			for (Interpret interpret : interprets) {
				try {
					Object result = interpret.interpret(input);
					input.confirm();
					if (result != null) {
						tokens.add(result);
					}
				} catch (NoInterpretationException e) {
					input.reset();
				}
			}
			throw new UnexpectedTokenException();
		}
		return tokens;
	}
}
