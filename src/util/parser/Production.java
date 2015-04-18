package util.parser;

public @interface Production {
	String[] symbol() default {};
	
	Symbol special() default Symbol.NONE;
	
	Class<?>[] maybe() default {};
	
	enum Symbol {
		NONE, SOF;
	}
}
