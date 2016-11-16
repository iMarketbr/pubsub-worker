package br.com.imarket.worker.mail.template;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TemplatePathResolver {
	
	@Value("${mail.template.url}")
	private String templateUrl;

	public String resolvePath(String templatePath) {
		return templateUrl + templatePath + ".html";
	}

}
