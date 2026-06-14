import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Projekat } from '../model/projekat';

@Injectable({
  providedIn: 'root',
})
export class ProjekatService {
  http = inject(HttpClient);

  apiUrl = "/api/projekti"

  public getAll(): Observable<Projekat[]>{
    return this.http.get<Projekat[]>(this.apiUrl);
  }

  public getProjekatById(id: number): Observable<Projekat>{
    return this.http.get<Projekat>(`${this.apiUrl}/${id}`);
  }
}
