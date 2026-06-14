package project_management.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import project_management.service.GenericService;



public abstract class GenericController<E> {
	
	protected abstract GenericService<E> getService();
	
	@GetMapping("")
	public ResponseEntity<List<E>> findAll(){
		List<E> entiteti = getService().findAll();
		
		return new ResponseEntity<>(entiteti,HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<E> findById(@PathVariable("id") Long id) {
		Optional<E> entitet = getService().findById(id);
		if (entitet.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(entitet.get(),HttpStatus.OK);
	}
	
	@PostMapping("")
	public ResponseEntity<E> create(@RequestBody E entitet) {
		E sacuvan = getService().save(entitet);
		return new ResponseEntity<>(sacuvan,HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<E> delete(@PathVariable("id") Long id){
		Optional<E> entitet = getService().findById(id);
		if (entitet.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		getService().deleteById(id);
		return new ResponseEntity<E>(HttpStatus.OK);
	}
	
	protected void updateEntity(E postojeci, E entitet) {
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<E> update(@PathVariable("id") Long id, @RequestBody E entitet){
		Optional<E> postojeci = getService().findById(id);
		if (postojeci.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		updateEntity(postojeci.get(),entitet);
		E sacuvan = getService().save(postojeci.get());
		return new ResponseEntity<>(sacuvan, HttpStatus.OK);
	}
	
	

}