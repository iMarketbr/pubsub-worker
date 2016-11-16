package br.com.imarket.worker.mail;

import static org.apache.http.entity.ContentType.TEXT_HTML;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.sendgrid.Content;
import com.sendgrid.Email;
import com.sendgrid.Mail;
import com.sendgrid.Method;
import com.sendgrid.Request;

@Service
class SendGridRequestFactory {

	Request createWithHtmlContent(EmailTemplate email, String htmlContent) {
		Mail mail = createMail(email, htmlContent);
		
		Request request = new Request();
		request.method = Method.POST;
		request.endpoint = "mail/send";

		try {
			request.body = mail.build();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return request;
	}

	private Mail createMail(EmailTemplate email, String htmlContent) {
		Email from = new Email(email.getFrom());
		Email to = new Email(email.getTo());
		String subject = email.getSubject();
		Content content = new Content(TEXT_HTML.getMimeType(), htmlContent);

		return new Mail(from, subject, to, content);
	}

}