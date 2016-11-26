package com.mastek.storm;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundingBox {
	@JsonProperty("coordinates")
	private Double [][][]coordinates;

	public Double[][][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Double[][][] coordinates) {
		this.coordinates = coordinates;
	}



}
