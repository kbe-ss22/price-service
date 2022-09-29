package com.kbe.priceservice.domain;

import com.kbe.priceservice.entity.PriceRequestCall;

public class PriceServiceUtilsImpl implements PriceServiceUtils{

    @Override
    public double sumPrices(PriceRequestCall requestCall) {
        double sum = 0;
        for (double value: requestCall.getPrices()) {
            sum += value;
        }
        return sum;
    }
}
