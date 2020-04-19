package com.asheesh.microservices.currencyconversionservice;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange-service", url = "localhost:8000")

// Below annotation is used to specify the application name on which 
// the service is hosted
//@FeignClient(name = "currency-exchange-service")

// Below annotation is used to route the Feign request via API Gateway
@FeignClient(name = "netflix-zuul-api-gateway-server")
@RibbonClient(name = "currency-exchange-service")
public interface CurrencyExchangeServiceProxy {

	// Below annotation is used with @FeignClient(name = "currency-exchange-service")
	//@GetMapping("/currency-exchange/from/{from}/to/{to}")
	
	// Below annotation is used with @FeignClient(name = "netflix-zuul-api-gateway-server")
	@GetMapping("/currency-exchange-service/currency-exchange/from/{from}/to/{to}")
	CurrencyConversionBean retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
