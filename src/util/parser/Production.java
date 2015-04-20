package util.parser;

public @interface Production {
	String[] symbol() default {};
	
	Symbol special() default Symbol.NONE;
	
	Class<?>[] maybe() default {};
	
	enum Symbol {
		/** no special attributes */
		NONE,
		/** may accept being the first production */
		SOI,
		/**
		 * may accept empty input, used to confirm a parsing ends correctly and
		 * to finalize parsing
		 */
		EOI;
	}
}
