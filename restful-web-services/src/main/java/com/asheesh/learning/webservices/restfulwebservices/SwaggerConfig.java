package com.asheesh.learning.webservices.restfulwebservices;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final Contact CUSTOM_CONTACT = new Contact("Asheesh Agarwal", "https://asheesh-agarwal.in",
			"asheesh.agarwal8123@gmail.com");
	public static final ApiInfo CUSTOM_API_INFO = new ApiInfo("My API Documentation Title",
			"My API Documentation Description", "1.0", "urn:tos", CUSTOM_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

	private static final Set<String> CUSTOM_PRODUCES_AND_CONSUMES = new HashSet(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(CUSTOM_API_INFO).produces(CUSTOM_PRODUCES_AND_CONSUMES);
	}

	/**
	 * Without this method, server won't start due to HATEOAS and Swagger
	 * conflict.
	 * 
	 * @return
	 */
	@Bean
	public LinkDiscoverers discovers() {
		List<LinkDiscoverer> plugins = new ArrayList<>();
		plugins.add(new CollectionJsonLinkDiscoverer());

		return new LinkDiscoverers(SimplePluginRegistry.create(plugins));
	}
}
