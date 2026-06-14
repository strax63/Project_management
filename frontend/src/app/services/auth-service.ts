import { Injectable, signal } from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  trenutniKorisnik = signal<string | null>(localStorage.getItem('username'));

  login(username: string, password: string): boolean {
    if (username === 'admin' && password === '12345'){
      localStorage.setItem('username',username);
      this.trenutniKorisnik.set(username);
      return true;
    }
    return false;
  }

  logout(): void{
    localStorage.removeItem('username');
    this.trenutniKorisnik.set(null)
  }

  isLoggedIn(): boolean{
    return this.trenutniKorisnik() !==null;

  }
}
