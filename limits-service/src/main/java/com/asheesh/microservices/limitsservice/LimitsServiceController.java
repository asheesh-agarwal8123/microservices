package com.asheesh.microservices.limitsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asheesh.microservices.limitsservice.bean.LimitConfiguration;

@RestController
public class LimitsServiceController {

	@Autowired
	private Configuration configuration;

	@GetMapping("/limits")
	public LimitConfiguration getLimitsConfig() {
		return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
	}
}
