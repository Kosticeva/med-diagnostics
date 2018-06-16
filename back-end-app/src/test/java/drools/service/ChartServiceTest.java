package drools.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Chart;
import drools.model.Doctor;
import drools.model.Examination;
import drools.model.Patient;
import drools.repository.ChartRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class ChartServiceTest {

	@Autowired
	ChartService chartService;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	PatientService patientService;
	
	@Autowired
	ExaminationService examinationService;
	
	@Autowired
	DoctorService doctorService;
	
	@Autowired
    private EntityManager em;
	
	public final Integer CHART_ID = 100;
	public final Integer NON_EX_CHART_ID = 200;
	
	@Test
	@Transactional
	public void testCreateNewChart() {
		
		Chart c = new Chart();
		c.setId(null);
		c.setPatient(patientService.findAll().get(0));
		c.setExaminations(new ArrayList<Examination>());
		
		List<Chart> allChartsMiddle = chartRepository.findAll();
		
		Chart cc = chartService.createNewChart(c);
		assertThat(cc.getId()).isNotNull();
		List<Chart> allChartsAfter = chartRepository.findAll();
		assertEquals(allChartsAfter.size(),allChartsMiddle.size()+1);
	}
	
	@Test
	@Transactional
	public void testUpdateNewChart() {
		
		Chart c = new Chart();
		c.setExaminations(new ArrayList<>());
		c = chartRepository.saveAndFlush(c);
		em.detach(c);
		
		c.setPatient(patientService.findAll().get(0));
		
		Examination e1 = new Examination();
		e1.setId(52465);
		Doctor ddddd = doctorService.findAll().get(0);
		e1.setDoctor(ddddd);
		e1 = examinationService.createNewExamination(e1);
		c.getExaminations().add(e1);
		
		List<Chart> allChartsBefore = chartRepository.findAll();
		assertThat(chartService.updateChart(c)).isNotNull();
		
		List<Chart> allChartsMiddle = chartRepository.findAll();
		assertEquals(allChartsBefore.size(),allChartsMiddle.size());
	}
	
	@Test
	@Transactional
	public void testCorrectChartPersistence() {
		Chart c = new Chart();
		c.setExaminations(new ArrayList<>());
		c = chartRepository.saveAndFlush(c);
		em.detach(c);
		
		assertThat(chartService.updateChart(c)).isNull();
		
		Patient p = new Patient("pp", "mm", 555, null);
		c.setPatient(p);
		assertThat(chartService.updateChart(c)).isNull();
		
		p.setAllergens(new ArrayList<>());
		p = patientService.savePatient(p);
		c.setPatient(p);
		c.setExaminations(null);
		
		assertThat(chartService.updateChart(c)).isNull();
		c.setExaminations(new ArrayList<>());
		
		Examination e1 = new Examination();
		e1.setId(52465);
		Doctor ddddd = doctorService.findAll().get(0);
		e1.setDoctor(ddddd);
		
		c.getExaminations().add(e1);
		assertThat(chartService.updateChart(c)).isNull();
		
		c.getExaminations().remove(e1);
		e1.setDate(null);
		e1 = examinationService.createNewExamination(e1);
		c.getExaminations().add(e1);
		
		assertThat(chartService.updateChart(c)).isNotNull();
	}
	
	
	@Test
	@Transactional
	public void testDeleteChart() throws SQLException{
		Chart c = new Chart();
		c = chartRepository.saveAndFlush(c);
		em.detach(c);
		
		List<Chart> allChartsBefore = chartRepository.findAll();
		chartService.deleteChart(c.getId());
		
		List<Chart> allChartsAfter = chartRepository.findAll();
		assertEquals(allChartsAfter.size(),allChartsBefore.size()-1);
	}
	
	@Test
	@Transactional
	public void testFindChart() {
		Chart c = new Chart();
		Patient p = new Patient("A", "B", 156, new ArrayList<>());
		p = patientService.savePatient(p);
		
		c.setPatient(p);
		
		c = chartRepository.saveAndFlush(c);
		em.detach(c);
		
		assertEquals(chartService.findById(c.getId()), c);
		assertThat(chartService.findById(NON_EX_CHART_ID)).isNull();
	}
	
	@Test
	@Transactional
	public void testFindAllChart() {
		
		List<Chart> allChartsBefore = chartRepository.findAll();
		Chart c1 = new Chart();
		Chart c2 = new Chart();
		
		c1 = chartRepository.saveAndFlush(c1);
		c2 = chartRepository.saveAndFlush(c2);
		
		List<Chart> allChartsAfter = chartRepository.findAll();
		assertEquals(allChartsAfter.size(),allChartsBefore.size()+2);
		
	}
}
