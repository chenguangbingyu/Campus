package com.campus.prime.bean;

public class EntityList<T extends ItemBase> extends PageBase {
	private T results;

	public T getResults() {
		return results;
	}

	public void setResults(T results) {
		this.results = results;
	}
	
}
