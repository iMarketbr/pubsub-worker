package br.com.imarket.worker.mail;

import java.util.HashMap;
import java.util.Map;

public class EmailContent {

	private Map<String, Object> content = new HashMap<>();

	public void put(String key, Object value) {
		this.content.put(key, value);
	}
	
	public Map<String, Object> getContent() {
		return content;
	}

}
