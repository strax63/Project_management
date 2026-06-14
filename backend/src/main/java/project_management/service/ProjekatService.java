package project_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_management.model.Projekat;
import project_management.repository.ProjekatRepository;



@Service
public class ProjekatService implements GenericService<Projekat> {
	@Autowired
	private ProjekatRepository projekatRepository;
	
	
	@Override
	public List<Projekat> findAll() {
		// TODO Auto-generated method stub
		return this.projekatRepository.findAll();
	}

	@Override
	public Optional<Projekat> findById(Long id) {
		// TODO Auto-generated method stub
		return this.projekatRepository.findById(id);
	}


	@Override
	public Projekat save(Projekat entitet) {
		// TODO Auto-generated method stub
		return this.projekatRepository.save(entitet);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.projekatRepository.deleteById(id);
	}

}
