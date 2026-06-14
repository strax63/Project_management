package project_management.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "zadaci")
public class Zadatak {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_projekta", nullable = false)
    private Projekat projekat;
    
    @JsonProperty("opis_zadatka")
    @Column(name = "opis_zadatka", nullable = false, columnDefinition = "TEXT")
    private String opisZadatka;
    
    @JsonProperty("status_zadatka")
    @Column(name = "status_zadatka", nullable = false)
    private Integer statusZadatka; // 0 - nije zapocet, 1 - u toku, 2 - zavrsen
    
    
    @JsonProperty("prioritet_zadatka")
    @Column(name = "prioritet_zadatka", nullable = false)
    private Integer prioritetZadatka; // 0 - nizak, 1 - srednji, 2 - visok, 3 - najvisi
    
    @JsonProperty("id_projekta")
    public Integer getIdProjekta() {
        return projekat != null ? projekat.getId() : null;
    }
    
    
    @JsonProperty("id_projekta")
    public void setIdProjekta(Integer idProjekta) {
        System.out.println("Setting id_projekta: " + idProjekta);
        if (idProjekta != null) {
            this.projekat = new Projekat();
            this.projekat.setId(idProjekta);
        }
    }
    public Zadatak() {}
    
    public Zadatak(Projekat projekat, String opisZadatka, Integer statusZadatka, Integer prioritetZadatka) {
        this.projekat = projekat;
        this.opisZadatka = opisZadatka;
        this.statusZadatka = statusZadatka;
        this.prioritetZadatka = prioritetZadatka;
    }
    
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public Projekat getProjekat() {
        return projekat;
    }
    
    public void setProjekat(Projekat projekat) {
        this.projekat = projekat;
    }
    
    public String getOpisZadatka() {
        return opisZadatka;
    }
    
    public void setOpisZadatka(String opisZadatka) {
        this.opisZadatka = opisZadatka;
    }
    
    public Integer getStatusZadatka() {
        return statusZadatka;
    }
    
    public void setStatusZadatka(Integer statusZadatka) {
        this.statusZadatka = statusZadatka;
    }
    
    public Integer getPrioritetZadatka() {
        return prioritetZadatka;
    }
    
    public void setPrioritetZadatka(Integer prioritetZadatka) {
        this.prioritetZadatka = prioritetZadatka;
    }
    
    
    public String getStatusOpis() {
        switch (statusZadatka) {
            case 0: return "Nije započet";
            case 1: return "U toku";
            case 2: return "Završen";
            default: return "Nepoznat";
        }
    }
    
    public String getPrioritetOpis() {
        switch (prioritetZadatka) {
            case 0: return "Nizak";
            case 1: return "Srednji";
            case 2: return "Visok";
            case 3: return "Najviši";
            default: return "Nepoznat";
        }
    }
    
    @Override
    public String toString() {
        return "Zadatak{" +
                "id=" + id +
                ", opisZadatka='" + opisZadatka + '\'' +
                ", statusZadatka=" + getStatusOpis() +
                ", prioritetZadatka=" + getPrioritetOpis() +
                '}';
    }
}