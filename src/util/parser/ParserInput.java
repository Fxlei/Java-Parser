package util.parser;

public interface ParserInput {
	public void confirm();
	public void reset();
	public char peek();
	public String peek(int length);
	public char read();
	public String read(int length);
}
