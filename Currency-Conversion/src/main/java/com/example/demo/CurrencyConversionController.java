package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from, @PathVariable String to,
                                                  @PathVariable BigDecimal quantity) {

        ExchangeValue forObject = restTemplate.getForObject(
                "http://FOREX-SERVICE/currency-exchange/from/" + from + "/to/" + to, ExchangeValue.class);


        return new CurrencyConversionBean(forObject.getId(), from, to, forObject.getConversionMultiple(), quantity,
                quantity.multiply(forObject.getConversionMultiple()), forObject.getPort());
    }

//    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
//    public CurrencyConversionBean convertCurrencyFeign(@PathVariable String from, @PathVariable String to,
//                                                       @PathVariable BigDecimal quantity) {
//
//        CurrencyConversionBean response = proxy.retrieveExchangeValue(from, to);
//
//        logger.info("{}", response);
//
//        return new CurrencyConversionBean(response.getId(), from, to, response.getConversionMultiple(), quantity,
//                quantity.multiply(response.getConversionMultiple()), response.getPort());
//    }
}