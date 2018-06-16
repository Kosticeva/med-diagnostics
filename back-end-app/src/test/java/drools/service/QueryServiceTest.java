package drools.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import drools.SampleApp;
import drools.model.Chart;
import drools.model.Disease;
import drools.model.Doctor;
import drools.model.Examination;
import drools.model.Link;
import drools.model.Patient;
import drools.model.Prescription;
import drools.model.Symptom;
import drools.repository.ChartRepository;
import drools.repository.DiseaseRepository;
import drools.repository.DoctorRepository;
import drools.repository.ExaminationRepository;
import drools.repository.LinkRepository;
import drools.repository.PatientRepository;
import drools.repository.PrescriptionRepository;
import drools.repository.SymptomRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SampleApp.class)
public class QueryServiceTest {

	@Autowired
	QueryService queryService;
	
	@Autowired
	DiseaseRepository diseaseRepository;
	
	@Autowired
	SymptomRepository symptomRepository;
	
	@Autowired
	LinkRepository linkRepository;
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	ChartRepository chartRepository;
	
	@Autowired
	DoctorRepository doctorRepository;
	
	@Autowired
	ExaminationRepository examRepository;
	
	@Autowired
	PrescriptionRepository presciptionRepository;
	//with data.sql
	
	@Test
	@Transactional
	public void checkAllPossibleDiseases() {
		List<Disease> diseases = diseaseRepository.findByNameStartingWithIgnoreCase("H");
		
		List<Link> links = linkRepository.findAll();
		
		List<Symptom> chosenSymptoms = new ArrayList<>();
		
		for(Disease d: diseases) {
			for(Link l: links) {
				if(l.getDisease().equals(d)) {
					chosenSymptoms.add(l.getSymptom());
					break;
				}
			}
		}
		
		List<Disease> allLinkedDiseases = new ArrayList<>();
		for(Symptom s: chosenSymptoms) {
			for(Link l: links) {
				if(l.getSymptom().equals(s)) {
					allLinkedDiseases.add(l.getDisease());
				}
			}
		}
		
		List<Disease> foundDiseases = queryService.getAllPossibleDiseases(chosenSymptoms);
		assertEquals(foundDiseases.size(), allLinkedDiseases.size());
	}
	
	@Test
	@Transactional
	public void checkSymptomsOfDisease() {
		Disease d = diseaseRepository.findAll().get(0);
		
		List<Link> links = linkRepository.findAll();
		List<Symptom> foundSymptoms = new ArrayList<>();
		
		for(Link l: links) {
			if(l.getDisease().equals(d)) {
				foundSymptoms.add(l.getSymptom());
			}
		}
		
		assertEquals(queryService.getDiseaseSymptoms(d).size(), foundSymptoms.size());
	}
	
	@Test
	@Transactional
	public void findMostPossibleDiseaseGroup1() {
		
		List<Symptom> s14 = symptomRepository.findByNameStartingWithIgnoreCase("Bol");
		List<Symptom> s23 = symptomRepository.findByNameStartingWithIgnoreCase("K");
		List<Symptom> s5 = symptomRepository.findByNameStartingWithIgnoreCase("Curenje");
		List<Symptom> s6 = symptomRepository.findByNameStartingWithIgnoreCase("Drhtavica");
		
		Patient p = new Patient("Bane", "Savic", null, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		Doctor d = doctorRepository.findAll().get(0);

		Examination ex = new Examination(null, new Date(), d, new ArrayList<>(), null, null);
		ex.getSymptoms().addAll(s14);
		ex.getSymptoms().addAll(s23);
		ex.getSymptoms().addAll(s5);
		ex.getSymptoms().addAll(s6);
		
		ex = examRepository.saveAndFlush(ex);
		
		Chart ch = new Chart(new ArrayList<>(), p, null);
		ch.getExaminations().add(ex);
		
		ch = chartRepository.saveAndFlush(ch);
		System.out.println("PREGLED 1\n"+ch);
		
		Disease most = queryService.getMostProbable(ex);
		System.out.println(most);
		assertEquals(most.getName(), "Prehlada");
	}
	
	@Test
	@Transactional
	public void findMostPossibleDiseaseGroup2() {
		List<Symptom> s = symptomRepository.findByNameStartingWithIgnoreCase("Povisen krvni pritisak");
		List<Symptom> nonimpS = symptomRepository.findByNameStartingWithIgnoreCase("Zamor");
		List<Symptom> nonimpS2 = symptomRepository.findByNameStartingWithIgnoreCase("Drhtavica");
		
		Patient p = new Patient("Nebojsa", "Savic", null, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		Doctor d = doctorRepository.findAll().get(0);

		Chart ch = new Chart(new ArrayList<>(), p, null);
		long firstOfJune2018 = 1527804000000L;
		long oneWeek = 1000*60*60*24*7L;
		
		
		List<Disease> ddd = diseaseRepository.findByNameStartingWithIgnoreCase("Groznica");
		Prescription ppp = presciptionRepository.findAll().get(0);
		
		for(int i=0; i<6; i++) {
			
			Examination ex = new Examination(null, new Date(firstOfJune2018), d, new ArrayList<>(), ddd.get(0), ppp);
			ex.getSymptoms().addAll(s);
			ex.getSymptoms().addAll(nonimpS);
			ex.getSymptoms().addAll(nonimpS2);
			
			ex = examRepository.saveAndFlush(ex);
			ch.getExaminations().add(ex);
			
			firstOfJune2018 -= oneWeek;
			
			Examination eX = new Examination(null, new Date(firstOfJune2018), d, new ArrayList<>(), ddd.get(0), ppp);
			eX.getSymptoms().addAll(s);
			eX.getSymptoms().addAll(nonimpS);
			eX.getSymptoms().addAll(nonimpS2);
			
			eX = examRepository.saveAndFlush(eX);
			ch.getExaminations().add(eX);
			
			firstOfJune2018 -= oneWeek;
		}
		
		ch = chartRepository.saveAndFlush(ch);
		System.out.println("PREGLED 2\n"+ch);
		
		Disease most = queryService.getMostProbable(ch.getExaminations().get(0));
		System.out.println(most);
		assertEquals(most.getName(), "Hipertenzija");
	}
	
	@Test
	@Transactional
	public void findMostPossibleDiseaseGroup3() {
		List<Symptom> s1 = symptomRepository.findByNameStartingWithIgnoreCase("Zamor");
		List<Symptom> s2 = symptomRepository.findByNameStartingWithIgnoreCase("Gusenje");
		List<Symptom> s3 = symptomRepository.findByNameStartingWithIgnoreCase("Oporavlja");
		List<Symptom> s4 = symptomRepository.findByNameStartingWithIgnoreCase("Otoci nogu i");
		List<Symptom> s5 = symptomRepository.findByNameStartingWithIgnoreCase("Temperatura veca");
		
		Patient p = new Patient("Katarina", "Vasic", null, new ArrayList<>());
		p = patientRepository.saveAndFlush(p);
		Doctor d = doctorRepository.findAll().get(0);

		Chart ch = new Chart(new ArrayList<>(), p, null);
		long firstOfJune2018 = 1527804000000L;
		
		List<Disease> diabetes = diseaseRepository.findByNameStartingWithIgnoreCase("Dijabetes");
		Prescription ppp = presciptionRepository.findAll().get(0);
		
		Examination diab = new Examination(null, new Date(firstOfJune2018-1000*60*60*24*365), d, new ArrayList<Symptom>(), diabetes.get(0), ppp);
		diab.getSymptoms().addAll(s5);
		diab.getSymptoms().addAll(s4);
		diab.getSymptoms().addAll(s1);
		diab = examRepository.saveAndFlush(diab);
		
		Examination recent = new Examination(null, new Date(firstOfJune2018-1000*60*60*24*5), d, new ArrayList<>(), diabetes.get(0), ppp);
		recent.getSymptoms().addAll(s5);
		recent.getSymptoms().addAll(s4);
		recent.getSymptoms().addAll(s3);
		recent.getSymptoms().addAll(s2);
		recent.getSymptoms().addAll(s1);
		recent  = examRepository.saveAndFlush(recent);
		
		Examination now = new Examination(null, new Date(firstOfJune2018), d, new ArrayList<>(), null, null);
		now.getSymptoms().addAll(s5);
		now.getSymptoms().addAll(s4);
		now.getSymptoms().addAll(s3);
		now.getSymptoms().addAll(s2);
		now.getSymptoms().addAll(s1);
		now  = examRepository.saveAndFlush(now);
		
		ch.getExaminations().add(diab);
		ch.getExaminations().add(recent);
		ch.getExaminations().add(now);
		
		ch = chartRepository.saveAndFlush(ch);
		
		System.out.println("PREGLED 3\n"+ch);
		
		
		Disease most = queryService.getMostProbable(ch.getExaminations().get(0));
		System.out.println(most);
		assertEquals(most.getName(), "Akutna bubrezna povreda");
	}
}
