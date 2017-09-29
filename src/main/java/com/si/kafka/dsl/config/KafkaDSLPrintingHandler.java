package com.si.kafka.dsl.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.context.IntegrationFlowContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.si.kafka.dsl.model.GetAllRunsResponse;
import com.si.kafka.dsl.model.RunsResult;

/**
 * 
 * @author sudhirc
 *
 */
@Configuration
public class KafkaDSLPrintingHandler {
	
	@Autowired
	private IntegrationFlowContext flowContext;
	
	@Value("${spring.kafka.bootstrap-servers}")
	private String kafkaBootstrapServers;
	
	@Value("${spring.kafka.template.new-topic}")
	private String kafkaTopic;
	
	@Autowired
	KafkaDSLProducingChannelConfigHttp2 ref;
	
	public void printMsg(String message) throws JsonParseException, JsonMappingException, IOException {
		
		//create ObjectMapper instance
		ObjectMapper objectMapper = new ObjectMapper();
		ref.runs.clear();
				
		//convert json string to object
		//List<RunsResult> myObjects = objectMapper.readValue(message, new TypeReference<List<RunsResult>>(){});
		GetAllRunsResponse res = objectMapper.readValue(message, GetAllRunsResponse.class);
		List<Integer> runIdsList = new ArrayList<>();
		for (RunsResult runsResult : res.getRuns()) {
			System.out.println("Object: "+runsResult.toString());
			runIdsList.add(runsResult.getRunId());
			ref.runs.add(runsResult.getRunId());
		}
		if(runIdsList.size()<1)
			return;
	
		
		
		IntegrationFlow intFlow = ref.integrationFlow();
		this.flowContext.registration(intFlow).register();
	}
	
}
