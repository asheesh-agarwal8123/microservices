package com.asheesh.service1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Service1Controller {
	
	@Autowired
	public Service2FeignClient service2FeignClient;

	@GetMapping("/service/name")
	public String getServiceName() {
		return "Service Name :: Service 1";
	}

	@GetMapping("/service/name/{greeting}")
	public String getServiceNameByGreeting(@PathVariable("greeting") String greetingMsg) {
		return greetingMsg + " Service Name :: Service 1";
	}
	
	@GetMapping("/otherservice/info")
	public String getOtherServiceInfo(){
		return service2FeignClient.getServiceName();
	}
	
	@GetMapping("/otherservice/info/{msg}")
	public String getOtherServiceInfo(@PathVariable("msg") String msg){
		return service2FeignClient.getServiceNameByGreeting(msg);
	}
}
