package project_management.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project_management.model.Zadatak;
import project_management.service.ZadatakService;

@RestController
@RequestMapping("/api/zadaci")
public class ZadatakController extends GenericController<Zadatak> {
    
    private final ZadatakService zadatakService;
    
    public ZadatakController(ZadatakService zadatakService) {
        this.zadatakService = zadatakService;
    }
    
    @Override
    protected ZadatakService getService() {
        return zadatakService;
    }
    
    @Override
    protected void updateEntity(Zadatak postojeci, Zadatak entitet) {
        // Postavljam samo polja koja se mogu menjati
        if (entitet.getOpisZadatka() != null) {
            postojeci.setOpisZadatka(entitet.getOpisZadatka());
        }
        if (entitet.getStatusZadatka() != null) {
            postojeci.setStatusZadatka(entitet.getStatusZadatka());
        }
        if (entitet.getPrioritetZadatka() != null) {
            postojeci.setPrioritetZadatka(entitet.getPrioritetZadatka());
        }
        
    }
    
    
    @GetMapping("/projekat/{projekatId}")
    public ResponseEntity<List<Zadatak>> getZadaciPoProjektu(@PathVariable("projekatId") Long projekatId) {
        List<Zadatak> zadaci = zadatakService.findByProjekatId(projekatId);
        return new ResponseEntity<>(zadaci, HttpStatus.OK);
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Zadatak>> getZadaciPoStatusu(@PathVariable("status") Integer status) {
        List<Zadatak> zadaci = zadatakService.findByStatus(status);
        return new ResponseEntity<>(zadaci, HttpStatus.OK);
    }
    
    @GetMapping("/prioritet/{prioritet}")
    public ResponseEntity<List<Zadatak>> getZadaciPoPrioritetu(@PathVariable("prioritet") Integer prioritet) {
        List<Zadatak> zadaci = zadatakService.findByPrioritet(prioritet);
        return new ResponseEntity<>(zadaci, HttpStatus.OK);
    }
    
    @GetMapping("/projekat/{projekatId}/status/{status}")
    public ResponseEntity<List<Zadatak>> getZadaciPoProjektuIStatusu(
            @PathVariable("projekatId") Long projekatId,
            @PathVariable("status") Integer status) {
        List<Zadatak> zadaci = zadatakService.findByProjekatIdAndStatus(projekatId, status);
        return new ResponseEntity<>(zadaci, HttpStatus.OK);
    }
}