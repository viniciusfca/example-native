package com.examplenative.business;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.examplenative.dto.CepDTO;
import com.examplenative.model.Cep;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.exc.StreamWriteException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RabbitMQBusiness {

	@Autowired
	@Lazy
	private CepBusiness cepBusiness;
	
	@Value("${rabbit.routing-key}")
	private String routingKey;
	@Value("${rabbit.exchange}")
	private String exchange;
	
	@Autowired
	private CachingConnectionFactory connect;

	// @RabbitListener(queues = { "${rabbit.queue:teste_performance_queue}" })
	public void receive(@Payload String message) throws JsonMappingException, JsonProcessingException {

		var objectMapper = new ObjectMapper();
		var cep = objectMapper.readValue(message, Cep.class);

		cepBusiness.saveCep(cep);

	}
	
	public void send(CepDTO cepDTO) throws StreamWriteException, DatabindException, IOException {
		var objectMapper = new ObjectMapper();
		RabbitTemplate template = new RabbitTemplate();
		template.setConnectionFactory(connect);
		
		template.convertAndSend(exchange, routingKey, objectMapper.writeValueAsString(cepDTO));
	}

}
