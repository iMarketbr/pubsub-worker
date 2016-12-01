package br.com.imarket.worker.market;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import br.com.imarket.worker.mail.EmailSender;
import br.com.imarket.worker.mail.EmailTemplate;
import br.com.imarket.worker.mail.EmailTemplate.EmailToBuilder;

@Component
class MarketCreatedMailSenderListener {

	@Autowired
	private EmailSender sendGridSender;
	@Autowired
	private MarketCreatedToEmailContentConverter converter;
	
	
	@Value("${mail.market.created.from}")
	private String emailFrom;
	@Value("${mail.market.created.subject}")
	private String emailSubject;
	@Value("${mail.market.created.template}")
	private String emailTemplate;
	
	@Async
	@EventListener
	void send(MarketCreatedEvent event) {
		MarketCreated marketCreated = event.getMarketCreated();
		
		EmailTemplate template = new EmailToBuilder(emailFrom)
						.to(marketCreated.getMarket().getLoginInfo().getEmail())
						.withSubject(emailSubject)
						.usingTemplate(emailTemplate)
						.withContent(converter.convert(marketCreated))
						.build();

		sendGridSender.send(template);
	}
}
