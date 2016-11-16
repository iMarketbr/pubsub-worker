package br.com.imarket.worker.subscription;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.cloud.pubsub.PubSub;

@Component
public class AsyncSubscription<T> {

	public static final Logger LOGGER = LoggerFactory.getLogger(AsyncSubscription.class);
	
	@Autowired
	private PubSub pubsub;
	@Autowired
	private ObjectMapper jacksonObjectMapper;

	public Pull from(Class<T> clazz) {
		return new Pull(clazz);
	}
	
	public class Pull {
		
		private Class<T> clazz;

		public Pull(Class<T> clazz) {
			this.clazz = clazz;
		}
		
		public void pull(String subscriptionName, AyncPull<T> callback) {
			pubsub.getSubscription(subscriptionName).pullAsync(message -> {
				LOGGER.info("Consuming market {}", message.getPayloadAsString());
				try {
					T t = jacksonObjectMapper.readValue(message.getPayloadAsString(), clazz);
					callback.received(t);
				} catch (JsonProcessingException e) {
					LOGGER.error("Could not deserialize message: {}", message.getPayloadAsString(), e);
				}
			});
		}
		
	}
}
