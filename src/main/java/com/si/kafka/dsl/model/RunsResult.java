package com.si.kafka.dsl.model;

import java.util.Date;

public class RunsResult {

	private Integer runId;
	private String runName;
	private Date runEnd;
	private String runQcStatus;
	private Integer numberOfBarcodes;
	private String sampleQcStatus;
	private String panelName;
	private String chipType;
	private String orderingSite;
	private String orderingTest;
	private String runComments;
	private Date runStart;
	private String runUrl;
	private String modifiedBy;
	private Date lastModified;
	private Instrument instrument;
	private Integer totalRunQcParameters;
	private Integer passedRunQcParameters;

	public RunsResult() {
	}

	public RunsResult(Integer runId, String runName, Date runEnd, String runQcStatus, Integer numberOfBarcodes,
			String sampleQcStatus, String panelName, String chipType, String orderingSite, String orderingTest,
			String runComments, Date runStart, String runUrl, String modifiedBy, Date lastModified,
			Instrument instrument, Integer totalRunQcParameters, Integer passedRunQcParameters) {
		super();
		this.runId = runId;
		this.runName = runName;
		this.runEnd = runEnd;
		this.runQcStatus = runQcStatus;
		this.numberOfBarcodes = numberOfBarcodes;
		this.sampleQcStatus = sampleQcStatus;
		this.panelName = panelName;
		this.chipType = chipType;
		this.orderingSite = orderingSite;
		this.orderingTest = orderingTest;
		this.runComments = runComments;
		this.runStart = runStart;
		this.runUrl = runUrl;
		this.modifiedBy = modifiedBy;
		this.lastModified = lastModified;
		this.instrument = instrument;
		this.totalRunQcParameters = totalRunQcParameters;
		this.passedRunQcParameters = passedRunQcParameters;
	}

	public Integer getRunId() {
		return runId;
	}

	public void setRunId(Integer runId) {
		this.runId = runId;
	}

	public String getRunName() {
		return runName;
	}

	public void setRunName(String runName) {
		this.runName = runName;
	}

	public Date getRunEnd() {
		return runEnd;
	}

	public void setRunEnd(Date runEnd) {
		this.runEnd = runEnd;
	}

	public String getRunQcStatus() {
		return runQcStatus;
	}

	public void setRunQcStatus(String runQcStatus) {
		this.runQcStatus = runQcStatus;
	}

	public Integer getNumberOfBarcodes() {
		return numberOfBarcodes;
	}

	public void setNumberOfBarcodes(Integer numberOfBarcodes) {
		this.numberOfBarcodes = numberOfBarcodes;
	}

	public String getSampleQcStatus() {
		return sampleQcStatus;
	}

	public void setSampleQcStatus(String sampleQcStatus) {
		this.sampleQcStatus = sampleQcStatus;
	}

	public String getPanelName() {
		return panelName;
	}

	public void setPanelName(String panelName) {
		this.panelName = panelName;
	}

	public String getChipType() {
		return chipType;
	}

	public void setChipType(String chipType) {
		this.chipType = chipType;
	}

	public String getOrderingSite() {
		return orderingSite;
	}

	public void setOrderingSite(String orderingSite) {
		this.orderingSite = orderingSite;
	}

	public String getOrderingTest() {
		return orderingTest;
	}

	public void setOrderingTest(String orderingTest) {
		this.orderingTest = orderingTest;
	}

	public String getRunComments() {
		return runComments;
	}

	public void setRunComments(String runComments) {
		this.runComments = runComments;
	}

	public Date getRunStart() {
		return runStart;
	}

	public void setRunStart(Date runStart) {
		this.runStart = runStart;
	}

	public String getRunUrl() {
		return runUrl;
	}

	public void setRunUrl(String runUrl) {
		this.runUrl = runUrl;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public Integer getTotalRunQcParameters() {
		return totalRunQcParameters;
	}

	public void setTotalRunQcParameters(Integer totalRunQcParameters) {
		this.totalRunQcParameters = totalRunQcParameters;
	}

	public Integer getPassedRunQcParameters() {
		return passedRunQcParameters;
	}

	public void setPassedRunQcParameters(Integer passedRunQcParameters) {
		this.passedRunQcParameters = passedRunQcParameters;
	}

	@Override
	public String toString() {
		return "RunsResult [runId=" + runId + ", runName=" + runName + ", runEnd=" + runEnd + ", runQcStatus="
				+ runQcStatus + ", numberOfBarcodes=" + numberOfBarcodes + ", sampleQcStatus=" + sampleQcStatus
				+ ", panelName=" + panelName + ", chipType=" + chipType + ", orderingSite=" + orderingSite
				+ ", orderingTest=" + orderingTest + ", runComments=" + runComments + ", runStart=" + runStart
				+ ", runUrl=" + runUrl + ", modifiedBy=" + modifiedBy + ", lastModified=" + lastModified
				+ ", instrument=" + instrument + ", totalRunQcParameters=" + totalRunQcParameters
				+ ", passedRunQcParameters=" + passedRunQcParameters + "]";
	}

}
