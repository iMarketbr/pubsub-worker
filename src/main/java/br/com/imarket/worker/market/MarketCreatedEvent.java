package br.com.imarket.worker.market;

public class MarketCreatedEvent {

	private final MarketCreated marketCreated;

	public MarketCreatedEvent(MarketCreated marketCreated) {
		this.marketCreated = marketCreated;
	}

	public MarketCreated getMarketCreated() {
		return marketCreated;
	}
	
}
