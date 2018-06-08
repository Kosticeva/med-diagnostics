package drools.model.reports;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import drools.model.Chart;

@Role(Role.Type.EVENT)
@Expires("10s")
public class HeartBeatEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Chart chart;
	
	public HeartBeatEvent() {}

	public HeartBeatEvent(Chart chart) {
		super();
		this.chart = chart;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
}
