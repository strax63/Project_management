package project_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project_management.model.Projekat;


public interface ProjekatRepository extends JpaRepository<Projekat,Long> {

}
