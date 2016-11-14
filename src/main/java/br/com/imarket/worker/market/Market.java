package br.com.imarket.worker.market;

public class Market {
	
	private Long id;
	private String name;
	private LoginInfo loginInfo;
	private String cnpj;
	private MarketAddress address;
	private boolean hasDelivery;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public String getCnpj() {
		return cnpj;
	}

	public MarketAddress getAddress() {
		return address;
	}

	public boolean isHasDelivery() {
		return hasDelivery;
	}
	
}
