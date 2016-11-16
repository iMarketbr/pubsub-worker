package br.com.imarket.worker.mail.template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.imarket.worker.mail.EmailTemplate;

@Service
public class TemplateProcessor {

	private TemplateEngine templateEngine;
	private TemplatePathResolver pathResolver;
	private ContentToContextConverter converter;

	@Autowired
	public TemplateProcessor(TemplateEngine templateEngine, TemplatePathResolver pathResolver, ContentToContextConverter converter) {
		this.templateEngine = templateEngine;
		this.pathResolver = pathResolver;
		this.converter = converter;
	}

	public String writeHtml(EmailTemplate email) {

		Context context = new Context();

		if (email.getContent().isPresent()) {
			context = converter.convert(email.getContent().get());
		}

		String templatePath = pathResolver.resolvePath(email.getTemplate());

		return templateEngine.process(templatePath, context);
	}	
}
