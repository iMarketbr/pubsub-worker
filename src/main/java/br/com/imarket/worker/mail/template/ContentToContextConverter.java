package br.com.imarket.worker.mail.template;

import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import br.com.imarket.worker.mail.EmailContent;

@Component
class ContentToContextConverter implements Converter<EmailContent, Context>{

	@Override
	public Context convert(EmailContent content) {
		Context context = new Context();
		((Map<String,Object>) content.getContent()).entrySet().forEach(keyValue -> {
			context.setVariable(keyValue.getKey(), keyValue.getValue());
		});
		return context;
	}

}
