package util.parser;

/**
 * Simple implementation of {@link ResetableParserInput} taking a {@link String}
 * as input.
 * 
 * @author Felix
 * @version 1.0
 */
public class ResetableStringParserInput implements ResetableParserInput {
	/**
	 * Last confirmed state of the input.
	 */
	private String input;
	/**
	 * Position of the actual state, relative to the last confirmed state.
	 */
	private int pos = 0;
	
	/**
	 * Creates a new {@link ResetableParserInput} with a {@link String}.
	 * 
	 * @param input
	 *            The {@link String} to parse.
	 */
	public ResetableStringParserInput(String input) {
		this.input = input;
	}
	
	@Override
	public char peek() {
		if (input.isEmpty()) {
			throw new EmptyParserInputException();
		}
		return input.charAt(pos);
	}
	
	@Override
	public String peek(int n) {
		return input.substring(Math.min(pos, input.length()), Math.min(pos + n, input.length()));
	}
	
	@Override
	public void advance() {
		pos++;
	}
	
	@Override
	public void advance(int n) {
		pos += n;
	}
	
	@Override
	public boolean isEmpty() {
		return input.length() <= pos;
	}
	
	@Override
	public void reset() {
		pos = 0;
	}
	
	@Override
	public void confirm() {
		input = input.substring(pos);
		pos = 0;
	}
	
}
