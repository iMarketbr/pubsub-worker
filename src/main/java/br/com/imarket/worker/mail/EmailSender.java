package br.com.imarket.worker.mail;

public interface EmailSender {

	void send(EmailTemplate template);
}
