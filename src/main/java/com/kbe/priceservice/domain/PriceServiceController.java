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
    private RabbitTemplate template;

    @Autowired
    private PriceServiceUtils priceServiceUtils;

    @RabbitListener(queues = RabbitConfig.QUEUEFORREQUESTS)
    private double receiveRequest(PriceRequestCall requestCall){
        return priceServiceUtils.sumPrices(requestCall);
    }
}
