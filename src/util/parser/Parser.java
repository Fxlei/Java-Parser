package util.parser;

import java.util.LinkedList;
import java.util.List;

public class Parser {
	private final List<ProductionParser> prods;
	private final List<String> keys;
	
	public Parser() {
		prods = new LinkedList<ProductionParser>();
		keys = new LinkedList<String>();
	}
	
	public void append(ProductionParser pp, String key) {
		insert(keys.size(), pp, key);
	}
	
	public void addAfter(String pre, ProductionParser pp, String key) {
		if (pre == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(pre) + 1, pp, key);
	}
	
	public void addBefore(String post, ProductionParser pp, String key) {
		if (post == null) {
			throw new NullPointerException();
		}
		insert(keys.indexOf(post), pp, key);
	}
	
	private void insert(int index, ProductionParser pp, String key) {
		if (index == -1) {
			throw new IllegalArgumentException("Relative ProductionParser does not exist.");
		}
		int original = keys.indexOf(key);
		if (original != -1) {
			keys.set(original, null);
		}
		keys.add(index, key);
		prods.add(index, pp);
	}
}
