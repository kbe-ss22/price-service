package com.kbe.priceservice.domain;

import com.kbe.priceservice.entity.PriceRequestCall;

public interface PriceServiceUtils {
    double sumPrices(PriceRequestCall requestCall);
}
