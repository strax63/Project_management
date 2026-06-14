package project_management.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_management.model.Projekat;
import project_management.service.ProjekatService;

@RestController
@RequestMapping("/api/projekti")
public class ProjekatController extends GenericController<Projekat> {
    
    private final ProjekatService projekatService;
    
    public ProjekatController(ProjekatService projekatService) {
        this.projekatService = projekatService;
    }
    
    @Override
    protected ProjekatService getService() {
        return projekatService;
    }
    
    @Override
    protected void updateEntity(Projekat postojeci, Projekat entitet) {
        
        if (entitet.getNaziv() != null) {
            postojeci.setNaziv(entitet.getNaziv());
        }
        if (entitet.getOpisProjekta() != null) {
            postojeci.setOpisProjekta(entitet.getOpisProjekta());
        }
        if (entitet.getRokRealizacije() != null) {
            postojeci.setRokRealizacije(entitet.getRokRealizacije());
        }
        
    }
}