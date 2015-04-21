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

	@Override
	public int length() {
		return 0;
	}

	@Override
	public char charAt(int index) {
		throw new IndexOutOfBoundsException("Index out of range: "+index);
	}

	@Override
	public CharSequence subSequence(int start, int end) {
		if(start==0&&end==0){
			return "";
		}else if(start==0){
			throw new IndexOutOfBoundsException("Index out of range: "+end);
		}else{
			throw new IndexOutOfBoundsException("Index out of range: "+start);
		}
	}
}
