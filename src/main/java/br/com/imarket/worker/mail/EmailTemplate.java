package br.com.imarket.worker.mail;

import java.util.Optional;

public class EmailTemplate {

	private String emailFrom;
	private String emailTo;
	private String subject;
	private EmailContent content;
	private String template;

	public String getTo() {
		return emailTo;
	}

	public String getSubject() {
		return subject;
	}

	public String getFrom() {
		return emailFrom;
	}

	public Optional<EmailContent> getContent() {
		return Optional.ofNullable(content);
	}

	public String getTemplate() {
		return template;
	}

	public void setContent(EmailContent content) {
		this.content = content;
	}

	public static EmailToBuilder from(String emailFrom) {
		return new EmailToBuilder(emailFrom);
	}

	public static class EmailToBuilder {
		private EmailTemplate email = new EmailTemplate();

		public EmailToBuilder(String emailFrom) {
			email.emailFrom = emailFrom;
		}

		public EmailSubjectBuilder to(String emailTo) {
			email.emailTo = emailTo;
			return new EmailSubjectBuilder();
		}

		public class EmailSubjectBuilder {
			public EmailTemplateBuilder withSubject(String subject) {
				email.subject = subject;
				return new EmailTemplateBuilder();
			}
		}

		public class EmailTemplateBuilder {

			public EmailBuilt usingTemplate(String template) {
				email.template = template;
				return new EmailBuilt();
			}
		}

		public class EmailBuilt {

			public EmailBuilt withContent(EmailContent content) {
				email.content = content;
				return this;
			}
			
			public EmailTemplate build() {
				return email;
			}
		}
	}
}
