package br.com.imarket.worker.market;

public class MarketCreatedEvent {

	private final Market market;

	public MarketCreatedEvent(Market market) {
		this.market = market;
	}
	
	public Market getMarket() {
		return market;
	}

}
