package br.com.imarket.worker.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Request;

import br.com.imarket.worker.mail.template.TemplateProcessor;

@Service
class SendGridEmailSender implements EmailSender {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendGridEmailSender.class);

	private SendGridClient sendGrid;
	private TemplateProcessor templateProcessor;
	private SendGridRequestFactory requestFactory;

	@Autowired
	SendGridEmailSender(SendGridClient sendGrid, TemplateProcessor processor, SendGridRequestFactory requestFactory) {
		this.sendGrid = sendGrid;
		this.templateProcessor = processor;
		this.requestFactory = requestFactory;
	}

	@Override
	public void send(EmailTemplate email) {
		String htmlContent = templateProcessor.writeHtml(email);
		Request request = requestFactory.createWithHtmlContent(email, htmlContent);
		sendGrid.send(request);
		LOGGER.info("Sent email from {} to {} with template {}", email.getFrom(), email.getTo(), email.getTemplate());
	}

}
