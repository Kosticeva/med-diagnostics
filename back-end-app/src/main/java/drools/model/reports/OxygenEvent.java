package drools.model.reports;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import drools.model.Chart;

@Role(Role.Type.EVENT)
@Expires("15m")
public class OxygenEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Chart chart;
	
	private double oxygenBloodLevel;
	
	public OxygenEvent() {}

	public OxygenEvent(Chart chart, double oxygenBloodLevel) {
		super();
		this.chart = chart;
		this.oxygenBloodLevel = oxygenBloodLevel;
	}

	public double getOxygenBloodLevel() {
		return oxygenBloodLevel;
	}

	public void setOxygenBloodLevel(double oxygenBloodLevel) {
		this.oxygenBloodLevel = oxygenBloodLevel;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
}