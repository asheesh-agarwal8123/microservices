package com.asheesh.learning.webservices.restfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import io.swagger.annotations.ApiOperation;

/**
 * This file gives the example of dynamic filtering of the property fields for
 * different APIs.
 * 
 * @author asheeshagarwal
 */

@RestController
public class FilteringController {

	@GetMapping("/filtering")
	@ApiOperation(value = "This API returns the SomeBean")
	public MappingJacksonValue getSomeBean() {
		SomeBean someBean = new SomeBean("Value1", "Value2", "Value3");

		FilterProvider filters = getSomeBeanFilterProvider("field1", "field2");

		MappingJacksonValue mappingValue = new MappingJacksonValue(someBean);
		mappingValue.setFilters(filters);

		return mappingValue;
	}

	private FilterProvider getSomeBeanFilterProvider(String... fields) {
		SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept(fields);

		// ID assigned to the filter needs to be added as JsonFilter annotation
		// on the Bean
		FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter", filter);
		return filters;
	}

	@GetMapping("/filtering-list")
	@ApiOperation(value = "This API returns the SomeBean List")
	public MappingJacksonValue getSomeBeanList() {
		List<SomeBean> someBeanList = Arrays.asList(new SomeBean("Value1", "Value2", "Value3"),
				new SomeBean("Value5", "Value6", "Value7"));

		FilterProvider filters = getSomeBeanFilterProvider("field2", "field3");

		MappingJacksonValue mappingValue = new MappingJacksonValue(someBeanList);
		mappingValue.setFilters(filters);

		return mappingValue;
	}
}
