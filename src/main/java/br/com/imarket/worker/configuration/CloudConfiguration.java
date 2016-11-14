package br.com.imarket.worker.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.cloud.AuthCredentials;
import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;

@Configuration
class CloudConfiguration {
	
	@Value("${cloud.auth.json.name}")
	private String gcloudJsonAuthName;
	@Value("${env}")
	private String env;
	
	@Bean
	AuthCredentials authCredentials() throws IOException  {
		if ("prod".equals(env)) {
			return AuthCredentials.createApplicationDefaults();
		}
		
		ClassLoader classLoader = getClass().getClassLoader();
		return AuthCredentials.createForJson(classLoader.getResourceAsStream(gcloudJsonAuthName));
	}

	@Bean
	PubSub pubSub(AuthCredentials authCredentials) {
		return PubSubOptions.newBuilder()
							.setAuthCredentials(authCredentials)
							.build()
							.getService();
	}
}
