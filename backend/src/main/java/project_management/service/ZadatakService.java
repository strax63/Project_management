package project_management.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project_management.model.Projekat;
import project_management.model.Zadatak;
import project_management.repository.ProjekatRepository;
import project_management.repository.ZadatakRepository;

@Service
public class ZadatakService implements GenericService<Zadatak> {

	@Autowired
	private ZadatakRepository zadatakRepository;
	@Autowired 
	private ProjekatRepository projekatRepository;
	
	@Override
	public List<Zadatak> findAll() {
		// TODO Auto-generated method stub
		return this.zadatakRepository.findAll();
	}

	@Override
	public Optional<Zadatak> findById(Long id) {
		// TODO Auto-generated method stub
		return this.zadatakRepository.findById(id);
	}


	// ZadatakService.java
	@Override
	public Zadatak save(Zadatak entitet) {
	    // Ako je projekat null ali ima idProjekta, učitaj projekat iz baze
	    if (entitet.getProjekat() == null && entitet.getIdProjekta() != null) {
	        Projekat projekat = projekatRepository.findById(Long.valueOf(entitet.getIdProjekta()))
	            .orElseThrow(() -> new RuntimeException("Projekat sa ID " + entitet.getIdProjekta() + " nije pronađen"));
	        entitet.setProjekat(projekat);
	    }
	    return zadatakRepository.save(entitet);
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		this.zadatakRepository.deleteById(id);
	}

	public List<Zadatak> findByProjekatId(Long projekatId) {
        return this.zadatakRepository.findByProjekatId(projekatId);
    }
    
    public List<Zadatak> findByStatus(Integer status) {
        return this.zadatakRepository.findByStatusZadatka(status);
    }
    
    public List<Zadatak> findByPrioritet(Integer prioritet) {
        return this.zadatakRepository.findByPrioritetZadatka(prioritet);
    }
    
    public List<Zadatak> findByProjekatIdAndStatus(Long projekatId, Integer status) {
        return this.zadatakRepository.findByProjekatIdAndStatusZadatka(projekatId, status);
    }
	

}

