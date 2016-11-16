package br.com.imarket.worker.configuration;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.google.cloud.AuthCredentials;
import com.google.cloud.pubsub.PubSub;
import com.google.cloud.pubsub.PubSubOptions;

@Configuration
class CloudConfiguration {
	
	@Value("${cloud.auth.json.name}")
	private String gcloudJsonAuthName;
	
	@Profile("dev")
	@Bean("authCredentials")
	AuthCredentials devAuthCredentials() throws IOException  {
		ClassLoader classLoader = getClass().getClassLoader();
		return AuthCredentials.createForJson(classLoader.getResourceAsStream(gcloudJsonAuthName));
	}
	
	@Profile("prod")
	@Bean("authCredentials")
	AuthCredentials prodAuthCredentials() throws IOException  {
		return AuthCredentials.createApplicationDefaults();
	}

	@Bean
	PubSub pubSub(AuthCredentials authCredentials) {
		return PubSubOptions.newBuilder()
							.setAuthCredentials(authCredentials)
							.build()
							.getService();
	}
}
