package br.com.imarket.worker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Environment {

	
	private final String env;

	public Environment(@Value("${spring.profiles.active}")  String env) {
		this.env = env;
	}
	
	public boolean isDev() {
		return !isProduction();
	}
	
	public boolean isProd() {
		return isProduction();
	}

	private boolean isProduction() {
		return "prod".equals(env);
	}

	public String get() {
		return env;
	}
}
