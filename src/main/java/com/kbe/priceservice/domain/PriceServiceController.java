package com.kbe.priceservice.domain;

import com.kbe.priceservice.config.RabbitConfig;
import com.kbe.priceservice.entity.PriceRequestCall;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PriceServiceController {

    @Autowired
    public RabbitTemplate template;

    @RabbitListener(queues = RabbitConfig.QUEUEFORREQUESTS)
    public double receiveRequest(PriceRequestCall requestCall){
        System.out.println("price calculation request call with id: " + requestCall.getId() + "received");
        double sum = sumPrices(requestCall);
        return sum;
        //PriceRequestAnswer answer = new PriceRequestAnswer(requestCall.getId(), sum);
        //template.convertAndSend(RabbitConfig.EXCHANGEFORANSWERS, RabbitConfig.ANSWERROUTINGKEY, answer);
        //System.out.println("price calculated for id: " + requestCall.getId() + ", with sum: " + sum);
    }

    public double sumPrices(PriceRequestCall requestCall){ //todo handle exceptions
        double sum = 0;
        for (double value: requestCall.getPrices()) {
            sum += value;
        }
        return sum;
    }
}
