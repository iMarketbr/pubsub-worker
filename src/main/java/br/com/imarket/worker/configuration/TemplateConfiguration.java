package br.com.imarket.worker.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templateresolver.TemplateResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

@Configuration
class TemplateConfiguration {

	@Bean
	TemplateResolver templateResolver() {
		TemplateResolver templateResolver = new UrlTemplateResolver();
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setOrder(1);
		templateResolver.setCharacterEncoding("UTF-8");

		templateResolver.setCacheable(true);
		return templateResolver;
	}
	
	@Bean
	@Autowired
	TemplateEngine createTemplateEngine(TemplateResolver templateResolver) {
		TemplateEngine templateEngine = new TemplateEngine();

		templateEngine.addTemplateResolver(templateResolver);

		return templateEngine;
	}
}
