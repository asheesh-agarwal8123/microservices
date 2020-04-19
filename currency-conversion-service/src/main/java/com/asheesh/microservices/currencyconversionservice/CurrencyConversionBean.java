package com.asheesh.microservices.currencyconversionservice;

import java.math.BigDecimal;

public class CurrencyConversionBean {

	private Long id;
	private String from;
	private String to;
	private BigDecimal conversionMultiple;
	private BigDecimal requestedQuantity;
	private BigDecimal totalCalculatedAmount;
	private int port;

	public CurrencyConversionBean() {
		super();
	}

	public CurrencyConversionBean(Long id, String from, String to, BigDecimal conversionMultiple,
			BigDecimal requestedQuantity, BigDecimal totalCalculatedAmount, int port) {
		super();
		this.id = id;
		this.from = from;
		this.to = to;
		this.conversionMultiple = conversionMultiple;
		this.requestedQuantity = requestedQuantity;
		this.totalCalculatedAmount = totalCalculatedAmount;
		this.port = port;
	}

	public Long getId() {
		return id;
	}

	public String getFrom() {
		return from;
	}

	public String getTo() {
		return to;
	}

	public BigDecimal getConversionMultiple() {
		return conversionMultiple;
	}

	public BigDecimal getRequestedQuantity() {
		return requestedQuantity;
	}

	public BigDecimal getTotalCalculatedAmount() {
		return totalCalculatedAmount;
	}

	public int getPort() {
		return port;
	}
}
