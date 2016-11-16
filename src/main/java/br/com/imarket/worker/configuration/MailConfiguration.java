package br.com.imarket.worker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sendgrid.SendGrid;

@Configuration
class MailConfiguration {
	
	@Value("${mail.sendgrid.api.key}")
	private String sendGridApiKey;
	
	@Bean
	SendGrid sendGrid() {
		return new SendGrid(sendGridApiKey);
	}
}
