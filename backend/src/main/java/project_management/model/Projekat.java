package project_management.model;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "projekti")
public class Projekat {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(name = "naziv", nullable = false, length = 255)
    private String naziv;
    
    @JsonProperty("opis_projekta")
    @Column(name = "opis_projekta", columnDefinition = "TEXT")
    private String opisProjekta;
    
    @JsonProperty("rok_realizacije")
    @Column(name = "rok_realizacije", nullable = false)
    private LocalDate rokRealizacije;
    
    @JsonManagedReference
    @OneToMany(mappedBy = "projekat", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Zadatak> zadaci = new ArrayList<>();
    
    
    public Projekat() {}
    
    public Projekat(String naziv, String opisProjekta, LocalDate rokRealizacije) {
        this.naziv = naziv;
        this.opisProjekta = opisProjekta;
        this.rokRealizacije = rokRealizacije;
    }
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getNaziv() {
        return naziv;
    }
    
    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }
    
    public String getOpisProjekta() {
        return opisProjekta;
    }
    
    public void setOpisProjekta(String opisProjekta) {
        this.opisProjekta = opisProjekta;
    }
    
    public LocalDate getRokRealizacije() {
        return rokRealizacije;
    }
    
    public void setRokRealizacije(LocalDate rokRealizacije) {
        this.rokRealizacije = rokRealizacije;
    }
    
    public List<Zadatak> getZadaci() {
        return zadaci;
    }
    
    public void setZadaci(List<Zadatak> zadaci) {
        this.zadaci = zadaci;
    }
    
    
    public void addZadatak(Zadatak zadatak) {
        zadaci.add(zadatak);
        zadatak.setProjekat(this);
    }
    
    public void removeZadatak(Zadatak zadatak) {
        zadaci.remove(zadatak);
        zadatak.setProjekat(null);
    }
    
    @Override
    public String toString() {
        return "Projekat{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", rokRealizacije=" + rokRealizacije +
                '}';
    }
}