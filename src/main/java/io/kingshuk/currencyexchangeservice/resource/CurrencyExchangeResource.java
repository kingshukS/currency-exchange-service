package io.kingshuk.currencyexchangeservice.resource;

import io.kingshuk.currencyexchangeservice.model.ExchangeValue;
import io.kingshuk.currencyexchangeservice.service.CurrencyExchangeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeResource {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeService currencyExchangeService;

    private static Logger logger = LoggerFactory.getLogger(CurrencyExchangeResource.class);

    @GetMapping("/from/{fromCurr}/to/{toCurr}")
    public ExchangeValue retrieveExchangeValue(@PathVariable("fromCurr") String fromCurr, @PathVariable("toCurr") String toCurr){
        ExchangeValue exchangeValue = currencyExchangeService.getExchangeValueByFromAndToCurrency(fromCurr, toCurr);
        exchangeValue.setPort(environment.getProperty("local.server.port"));
        logger.info("--CurrencyExchangeResource.retrieveExchangeValue(): exchange value={}",exchangeValue);
        return exchangeValue;
    }
}
