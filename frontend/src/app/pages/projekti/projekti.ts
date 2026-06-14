import { Component, inject, OnInit, signal } from '@angular/core';
import { ProjekatService } from '../../services/projekat-service';
import { Router, RouterLink } from '@angular/router';
import { Projekat } from '../../model/projekat';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AuthService } from '../../services/auth-service';

@Component({
  selector: 'app-projekti',
  imports: [RouterLink,MatToolbarModule, MatCardModule, MatButtonModule, MatIconModule],
  templateUrl: './projekti.html',
  styleUrl: './projekti.css',
})
export class Projekti implements OnInit {
  router = inject(Router);
  authService = inject(AuthService);
  projekatService = inject(ProjekatService);

  projekti = signal<Projekat[]>([])


  ngOnInit(): void {
    this.projekatService.getAll().subscribe(
      (res) => {this.projekti.set(res)}
    )
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}
