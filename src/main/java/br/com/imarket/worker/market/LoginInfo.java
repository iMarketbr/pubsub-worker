package br.com.imarket.worker.market;

import br.com.imarket.worker.login.LoginOrigin;
import br.com.imarket.worker.login.LoginType;

public class LoginInfo {

	private Long id;
	private String email;
	private String password;
	private LoginOrigin loginOrigin;
	private LoginType loginType;

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public LoginOrigin getLoginOrigin() {
		return loginOrigin;
	}

	public LoginType getLoginType() {
		return loginType;
	}

}
