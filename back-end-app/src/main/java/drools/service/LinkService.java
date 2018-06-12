package drools.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Disease;
import drools.model.Link;
import drools.model.Symptom;
import drools.repository.LinkRepository;

@Service
public class LinkService {

	@Autowired
	DiseaseService diseaseService;
	
	@Autowired
	SymptomService symptomService;
	
	@Autowired
	LinkRepository linkRepository;
	
	@Transactional
	public Link connect(Integer diseaseId, Integer symptomId) {
		
		Disease d = diseaseService.findById(diseaseId);
		Symptom s = symptomService.findById(symptomId);
		
		if(d == null || s == null) {
			System.out.println("Los simptomm / bolest");
			return null;
		}
		
		if(linkRepository.findByDiseaseAndSymptom(d, s) != null) {
			System.out.println("Vec postoji ovakav par!");
		}
		
		return linkRepository.save(new Link(d, s));
	}
	
	@Transactional
	public Link disconnect(Integer diseaseId, Integer symptomId) {
		Disease d = diseaseService.findById(diseaseId);
		Symptom s = symptomService.findById(symptomId);
		
		if(d == null || s == null) {
			System.out.println("Los simptomm / bolest");
			return null;
		}
		
		Link l = linkRepository.findByDiseaseAndSymptom(d, s);
		linkRepository.delete(l);
		
		return l;
	}
	
	@Transactional
	public List<Link> getAllLinks(){
		return linkRepository.findAll();
	}
}
