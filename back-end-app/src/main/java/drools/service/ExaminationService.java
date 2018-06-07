package drools.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import drools.model.Examination;
import drools.repository.ExaminationRepository;

@Service
public class ExaminationService {
	
	@Autowired
	ExaminationRepository examinationRepository;
	
	public Examination findById(int id) {
		return examinationRepository.getOne(id);
	}
	
	public List<Examination> findAll(){
		return examinationRepository.findAll();
	}
	
	public Examination createNewExamination(Examination examination) {
		return examinationRepository.save(examination);
	}
	
	public Examination updateExamination(Examination examination) {
		return examinationRepository.save(examination);
	}
	
	public void deleteExamination(int id) {
		examinationRepository.delete(id);
	}
}