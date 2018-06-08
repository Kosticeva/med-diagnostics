package drools.model.reports;

import java.io.Serializable;

import org.kie.api.definition.type.Expires;
import org.kie.api.definition.type.Role;

import drools.model.Chart;

@Role(Role.Type.EVENT)
@Expires("12h")
public class DialysisEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Chart chart;
	
	private double urineQuantity;
	
	public DialysisEvent() {}

	public DialysisEvent(Chart chart, double urineQuantity) {
		super();
		this.chart = chart;
		this.urineQuantity = urineQuantity;
	}

	public double getUrineQuantity() {
		return urineQuantity;
	}

	public void setUrineQuantity(double urineQuantity) {
		this.urineQuantity = urineQuantity;
	}

	public Chart getChart() {
		return chart;
	}

	public void setChart(Chart chart) {
		this.chart = chart;
	}
}
