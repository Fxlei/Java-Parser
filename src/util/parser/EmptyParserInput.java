package util.parser;

public class EmptyParserInput implements ResetableParserInput {
	
	@Override
	public char peek() {
		throw new EmptyParserInputException();
	}
	
	@Override
	public String peek(int n) {
		return "";
	}
	
	@Override
	public void advance() {
	}
	
	@Override
	public void advance(int n) {
	}
	
	@Override
	public boolean isEmpty() {
		return true;
	}
	
	@Override
	public void reset() {
	}
	
	@Override
	public void confirm() {
	}
	
}
