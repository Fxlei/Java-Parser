package util.tokenizer;

import java.util.regex.Pattern;


public class SymbolInterpretBuilder {
	public StringBuilder sb = new StringBuilder("^");
	
	public void addSymbol(String symbol) {
		if (sb.length() == 1) {
			sb.append(symbol);
		} else {
			sb.append("|").append(symbol);
		}
	}
	
	public void addSymbol(char symbol) {
		if (sb.length() == 1) {
			sb.append(symbol);
		} else {
			sb.append("|").append(symbol);
		}
	}
	
	public SymbolInterpret build(){
		return new SymbolInterpret(Pattern.compile(sb.toString()));
	}
}
