package br.com.imarket.worker.market;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import br.com.imarket.worker.subscription.AsyncSubscription;

@Component
public class MarketCreatedConsumer {

	@Value("${cloud.pubsub.subscription.market.created.email}")
	private String subscriptionName;
	@Autowired
	private AsyncSubscription<Market> subscription;
	
	@PostConstruct
	public void consume() {
		subscription.from(Market.class).pull(subscriptionName, market -> {
			System.out.println("Received market: " + market.getName());
		});
	}
}
