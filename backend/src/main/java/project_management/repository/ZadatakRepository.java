package project_management.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import project_management.model.Zadatak;


public interface ZadatakRepository extends JpaRepository<Zadatak,Long> {

	
	List<Zadatak> findByProjekatId(Long projekatId);
    
    List<Zadatak> findByStatusZadatka(Integer status);
    
    List<Zadatak> findByPrioritetZadatka(Integer prioritet);
    
    List<Zadatak> findByProjekatIdAndStatusZadatka(Long projekatId, Integer status);
}
