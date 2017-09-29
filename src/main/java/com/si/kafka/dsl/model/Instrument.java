package com.si.kafka.dsl.model;

public class Instrument {

	String siteId;
	String modelName;
	String manufacturer;

	public Instrument() {

	}

	public Instrument(String siteId, String modelName, String manufacturer) {
		super();
		this.siteId = siteId;
		this.modelName = modelName;
		this.manufacturer = manufacturer;
	}

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	@Override
	public String toString() {
		return "Instrument [siteId=" + siteId + ", modelName=" + modelName + ", manufacturer=" + manufacturer + "]";
	}

}
