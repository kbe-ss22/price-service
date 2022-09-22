package com.kbe.priceservice.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGEFORANSWERS = "priceservice-answer-exchange";

    public static final String QUEUEFORANSWERS = "priceservice-answer-queue";
    public static final String QUEUEFORREQUESTS = "priceservice-request-queue";
    public static final String ANSWERROUTINGKEY = "output.productservice";

    @Bean
    public Queue requestQueue() {
        return new Queue(QUEUEFORREQUESTS, false);
    }
    @Bean
    public Queue answerQueue() {
        return new Queue(QUEUEFORANSWERS, false);
    }
    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGEFORANSWERS);
    }

    @Bean
    public Binding binding(TopicExchange exchange) {
        return BindingBuilder.bind(answerQueue()).to(exchange).with(ANSWERROUTINGKEY);
    }


    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate template(ConnectionFactory connectionFactory){
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(messageConverter());//template.setMessageConverter(converter); //template.setMessageConverter(messageConverter());
        return template;
    }



}
