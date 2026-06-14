import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ProjekatService } from '../../services/projekat-service';
import { Projekat } from '../../model/projekat';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-projekti-details',
  imports: [RouterLink,MatToolbarModule, MatCardModule, MatButtonModule, MatIconModule, MatDividerModule],
  templateUrl: './projekti-details.html',
  styleUrl: './projekti-details.css',
})
export class ProjektiDetails implements OnInit {

  route = inject(ActivatedRoute);

  projekatService = inject(ProjekatService);

  projekat = signal<Projekat | null>(null);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    this.projekatService.getProjekatById(id).subscribe(
      (res) => {this.projekat.set(res)}
    )
  }
}
