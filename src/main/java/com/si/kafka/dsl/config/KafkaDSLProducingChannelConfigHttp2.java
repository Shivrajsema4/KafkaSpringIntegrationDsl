package com.si.kafka.dsl.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.MessageHandler;


/**
 * 
 * @author sudhirc
 *
 */
@Configuration
@PropertySource(value = { "classpath:application.properties" })
public class KafkaDSLProducingChannelConfigHttp2 {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	
	@Value("${spring.kafka.template.new-topic}")
	private String kafkaTopic;
	
	List<Integer> runs = new ArrayList<>();
	
	public KafkaDSLProducingChannelConfigHttp2() {
		
	}
	
	public MessageHandler kafkaMessageHandler() {
		KafkaProducerMessageHandler<String, String> messaheHandler = new KafkaProducerMessageHandler<>(kafkaTemplate());
		messaheHandler.setMessageKeyExpression(new LiteralExpression("spring-itegration"));
		messaheHandler.setTopicExpression(new LiteralExpression(kafkaTopic));
		return messaheHandler;
	}
	
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	public Map<String, Object> producerConfigs() {
		Map<String, Object> properties = new HashMap<>();
	    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
	    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    // introduce a delay on the send to allow more messages to accumulate
	    properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	    
	    return properties;
	}
	
	public KafkaDSLProducingChannelConfigHttp2(List<Integer> runs) {
		this.runs=runs;
	}
	
	public MessageSource<?> httpSource() {
		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(this);
        source.setMethodName("getResponse");
        return source;
	}

	public String getResponse() {
		return runs.toString();
		
	}
	public DirectChannel stringInboundChannel() {
		return new DirectChannel();
	}
	
	public DirectChannel directChannel() {
		return new DirectChannel();
	}
	
	
	public ObjectToStringTransformer toStringTransformer() {
		return new ObjectToStringTransformer();
	}

	public IntegrationFlow integrationFlow() {
		return IntegrationFlows.from(httpSource(), c -> c.poller(Pollers.fixedRate(1000000000).maxMessagesPerPoll(1)))
				.channel(directChannel())
				.transform(toStringTransformer())
				.channel(stringInboundChannel())
				.handle(kafkaMessageHandler())
				.get();
	}

}
