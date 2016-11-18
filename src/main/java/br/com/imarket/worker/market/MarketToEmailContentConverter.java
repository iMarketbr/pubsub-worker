package br.com.imarket.worker.market;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.imarket.worker.mail.EmailContent;

@Component
public class MarketToEmailContentConverter implements Converter<Market, EmailContent>{
	
	@Value("${web.base.url}")
	private String webUrl;

	@Override
	public EmailContent convert(Market market) {
		EmailContent content = new EmailContent();
		
		content.put("market", market.getName());
		content.put("email", market.getLoginInfo().getEmail());
		content.put("password", market.getLoginInfo().getPassword());
		content.put("webUrl", webUrl + "/login");
		
		return content;
	}

}
