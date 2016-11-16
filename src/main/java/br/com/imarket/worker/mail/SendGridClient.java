package br.com.imarket.worker.mail;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;

@Service
class SendGridClient {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SendGridClient.class);

	@Autowired
	private SendGrid sendGrid;

	void send(Request request) {
		try {
			Response response = sendGrid.api(request);

			if(hasError(response)) {
				throw new CannotSendMailException("StatusCode: " + response.statusCode + "ResponseBody: " + response.body);
			}

			LOGGER.debug("Email sent to SendGrid: " + request.body);

		} catch (IOException e) {
			throw new CannotSendMailException("Unable to send request to SendGrid.",  e);
		}
	}

	private boolean hasError(Response response) {
		return response.statusCode / 100 != 2;
	}

}
