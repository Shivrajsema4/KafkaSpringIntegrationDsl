package com.si.kafka.dsl.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.common.LiteralExpression;
import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.core.Pollers;
import org.springframework.integration.endpoint.MethodInvokingMessageSource;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.integration.kafka.outbound.KafkaProducerMessageHandler;
import org.springframework.integration.transformer.ObjectToStringTransformer;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.messaging.MessageHandler;
import org.springframework.web.client.RestTemplate;


/**
 * 
 * @author sudhirc
 *
 */
@Configuration
public class KafkaDSLProducingChannelConfigHttp {
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	
	@Value("${spring.kafka.template.default-topic}")
	private String kafkaTopic;
	
	@Value("${input.directory}")
	private File directory;
	
	@Autowired
	BeanFactory beanFactory;
	
	@Bean
	public MessageSource<?> httpSource() {
		MethodInvokingMessageSource source = new MethodInvokingMessageSource();
        source.setObject(new KafkaDSLProducingChannelConfigHttp());
        source.setMethodName("getResponse");
        source.setBeanFactory(beanFactory);
        return source;
	}
	
	@Bean
	public ObjectToStringTransformer toStringTransformer() {
		return new ObjectToStringTransformer();
	}

	@Bean
	public MessageHandler kafkaMessageHandler() {
		KafkaProducerMessageHandler<String, String> messaheHandler = new KafkaProducerMessageHandler<>(kafkaTemplate());
		messaheHandler.setMessageKeyExpression(new LiteralExpression("spring-itegration"));
		messaheHandler.setTopicExpression(new LiteralExpression(kafkaTopic));
		return messaheHandler;
	}
	
	@Bean
	public KafkaTemplate<String, String> kafkaTemplate() {
		return new KafkaTemplate<>(producerFactory());
	}
	
	@Bean
	public ProducerFactory<String, String> producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}
	
	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> properties = new HashMap<>();
	    properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
	    properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	    // introduce a delay on the send to allow more messages to accumulate
	    properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
	    
	    return properties;
	}
	

	@Bean
	public DirectChannel producingChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public DirectChannel stringInboundChannel() {
		return new DirectChannel();
	}
	
	@Bean
	public LoggingHandler loggingHandler() {
		return new LoggingHandler("info");
	}

	@Bean
	public String getResponse() {
		RestTemplate rst= new RestTemplate();
		return rst.getForObject("http://localhost:9090/cp-os-dashboard/runs",String.class );
		
	}
	
	@Bean
	public IntegrationFlow integrationFlow() {
		return IntegrationFlows.from(httpSource(), c -> c.poller(Pollers.fixedRate(5000).maxMessagesPerPoll(1)))
				.channel(producingChannel())
				.transform(toStringTransformer())
				.channel(stringInboundChannel())
				.handle(kafkaMessageHandler())
				.get();
	}
	

	 @MessagingGateway(defaultRequestChannel = "httpOut.input")
	    public interface Gateway {
	        public String exchange(String out);
	    }
	 
	 /*@Bean
	public IntegrationFlow integrationFlow() {
		return f -> f.handle(Http.outboundGateway("http://localhost:9090/cp-os-dashboard/runs"))
				.channel(producingChannel())
				.transform((String s) -> s.toUpperCase())
				.channel(stringInboundChannel()).handle(kafkaMessageHandler())
				;
	}*/
	
}
