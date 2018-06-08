package drools.model;

import java.util.Date;

public class IntensiveCareReport {
	
	private boolean heartOk;
	
	private boolean oxygenOk;
	
	private boolean urinOk;
	
	private Chart chart;

	private Date timeStamp;
	
	public IntensiveCareReport(boolean heartOk, boolean oxygenOk, boolean urinOk, Chart chart) {
		super();
		this.heartOk = heartOk;
		this.oxygenOk = oxygenOk;
		this.urinOk = urinOk;
		this.chart = chart;
		this.timeStamp = new Date();
	}
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public IntensiveCareReport() {}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}

	public boolean isHeartOk() {
		return heartOk;
	}

	public void setHeartOk(boolean heartOk) {
		this.heartOk = heartOk;
	}

	public boolean isOxygenOk() {
		return oxygenOk;
	}

	public void setOxygenOk(boolean oxygenOk) {
		this.oxygenOk = oxygenOk;
	}

	public boolean isUrinOk() {
		return urinOk;
	}

	public void setUrinOk(boolean urinOk) {
		this.urinOk = urinOk;
	}
	
}
