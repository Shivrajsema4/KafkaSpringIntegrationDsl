package com.si.kafka.dsl.model;

import java.util.List;

public class GetAllRunsResponse {

	private List<RunsResult> runs;
	private Long total;

	public GetAllRunsResponse() {
	}

	public GetAllRunsResponse(List<RunsResult> runs, Long total) {
		super();
		this.runs = runs;
		this.total = total;
	}

	public List<RunsResult> getRuns() {
		return runs;
	}

	public void setRuns(List<RunsResult> runs) {
		this.runs = runs;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "GetAllRunsResponse [runs=" + runs + ", total=" + total + "]";
	}

}
