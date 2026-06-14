import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Zadatak } from '../model/zadatak';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ZadatakService {
  http = inject(HttpClient);

  apiUrl = "/api/zadaci";

  public getAll(): Observable<Zadatak[]>{
      return this.http.get<Zadatak[]>(this.apiUrl);
    }
  
  public getZadatakById(id: number): Observable<Zadatak>{
      return this.http.get<Zadatak>(`${this.apiUrl}/${id}`);
    }

  public createZadatak(zadatak: Partial<Zadatak>): Observable<Zadatak>{
    return this.http.post<Zadatak>(this.apiUrl, zadatak);
    }

  public updateZadatak(id: number, zadatak: Partial<Zadatak>): Observable<Zadatak>{
    return this.http.put<Zadatak>(`${this.apiUrl}/${id}`, zadatak);
    }

  public deleteZadatak(id: number): Observable<void>{
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
    }
}
