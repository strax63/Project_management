# Docker Projekat - Trosojna Arhitektura

## Opis projekta

Ovaj projekat predstavlja kontejnerizovano rešenje realizovano kao **troslojna arhitektura** sa potpunom izolacijom servisa korišćenjem Docker mreža.

### Tehnološki stek

Sloj | Tehnologija
| **Frontend** | Angular |
| **Backend** | Spring Boot |
| **Baza podataka** | PostgreSQL |
| **Reverse proxy** | Nginx |

## Arhitektura sistema

### Mrežna arhitektura

Sistem koristi dva odvojena Docker bridge drajvera za potpunu izolaciju servisa:

- ** Javna (public) mreža**
  - Eksterno orijentisano okruženje
  - Mapiran port `80:80`
  - Jedina komponenta: `reverse-proxy`
  - Sprečava direktno izlaganje ostalih servisa javnom internetu

- ** Interna (internal) mreža**
  - Izolovano privatno okruženje
  - Namenjena isključivo za međusobnu komunikaciju kontejnera
  - Spoljni saobraćaj nema direktan pristup

## Kontejnerizacija

### Principi dizajna

- **Jedna odgovornost** – svaki servis radi u sopstvenom kontejneru
- **Bezbednost** – baza podataka i interni servisi nisu vidljivi spolja
- **Centralizacija** – Nginx jedini ima otvoren port ka hostu
- **Trajnost podataka** – Docker volume za čuvanje baze podataka
- **Jednostavno pokretanje** – `docker-compose.yml` za ceo sistem
