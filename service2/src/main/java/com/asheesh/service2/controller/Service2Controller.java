package com.asheesh.service2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
public class Service2Controller {
	
	@Autowired
	public Service1FeignClient service1FeignClient;

	@GetMapping("/service/name")
	public String getServiceName() {
		return "Service Name :: Service 2";
	}

	@GetMapping("/service/name/{greeting}")
	public String getServiceNameByGreeting(@PathVariable("greeting") String greetingMsg) {
		return greetingMsg + " Service Name :: Service 2";
	}
	
	@GetMapping("/otherservice/info")
	public String getOtherServiceInfo(){
		return service1FeignClient.getServiceName();
	}
	
	@GetMapping("/otherservice/info/{msg}")
	public String getOtherServiceInfo(@PathVariable("msg") String msg){
		return service1FeignClient.getServiceNameByGreeting(msg);
	}
}
