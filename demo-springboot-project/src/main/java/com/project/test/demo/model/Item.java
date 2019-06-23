package com.project.test.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Item {

	private Long id;
	private String description;
	private Long quantity;
	private Float unitaryValue;

	@JsonCreator
	public Item(@JsonProperty("description") String description, @JsonProperty("quantity") Long quantity,
			@JsonProperty("unitaryValue") Float unitaryValue) {
		super();
		this.description = description;
		this.quantity = quantity;
		this.unitaryValue = unitaryValue;
	}
	
	public Item(Long id, String description, Long quantity,Float unitaryValue) {
		super();
		this.id = id;
		this.description = description;
		this.quantity = quantity;
		this.unitaryValue = unitaryValue;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Float getUnitaryValue() {
		return unitaryValue;
	}

	public void setUnitaryValue(Float unitaryValue) {
		this.unitaryValue = unitaryValue;
	}

	@Override
	public String toString() {
		String info = String.format("Item Info: description = %s, quantity = %s, unitaryValue = %f", description,
				quantity, unitaryValue);
		return info;
	}

}
