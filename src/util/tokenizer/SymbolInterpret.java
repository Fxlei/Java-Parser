package util.tokenizer;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import util.parser.ParserInput;

public class SymbolInterpret implements Interpret {
	public Pattern p;

	@Override
	public Symbol interpret(ParserInput input) {
		Matcher m = p.matcher(input);
		if(m.find()){
			String result = m.group(0);
			input.advance(result.length());
			return new Symbol(result);
		}else{
			return null;
		}
	}
	
	public SymbolInterpret(Pattern p) {
		this.p = p;
	}
}
