package com.asheesh.service2.controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service1")
public interface Service1FeignClient {

	@GetMapping("/service/name")
	String getServiceName();

	@GetMapping("/service/name/{greetingMsg}")
	String getServiceNameByGreeting(@PathVariable("greetingMsg") String greetingMsg);
}
