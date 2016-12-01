package br.com.imarket.worker.market;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.com.imarket.worker.mail.EmailContent;

@Component
public class MarketCreatedToEmailContentConverter implements Converter<MarketCreated, EmailContent>{
	
	@Value("${web.change.password.url}")
	private String webChangePasswordUrl;

	@Override
	public EmailContent convert(MarketCreated marketCreated) {
		Market market = marketCreated.getMarket();
		EmailContent content = new EmailContent();
		
		String webChangePasswordUrlWithToken = webChangePasswordUrl 
						+ "?id=" + market.getId() 
						+ "&token=" + marketCreated.getPasswordToken();
		
		content.put("market", market.getName());
		content.put("webUrl", webChangePasswordUrlWithToken);
		
		return content;
	}

}
