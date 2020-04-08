package com.asheesh.service1.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service2")
public interface Service2FeignClient {

	@GetMapping("/service/name")
	String getServiceName();

	@GetMapping("/service/name/{greetingMsg}")
	String getServiceNameByGreeting(@PathVariable("greetingMsg") String greetingMsg);
}
