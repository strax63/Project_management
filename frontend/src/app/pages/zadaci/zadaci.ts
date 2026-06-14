import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterModule } from '@angular/router';
import { ZadatakService } from '../../services/zadatak-service';
import { Zadatak } from '../../model/zadatak';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { ZadatakForma } from '../../forms/zadatak-forma/zadatak-forma';

@Component({
  selector: 'app-zadaci',
  imports: [RouterModule, MatToolbarModule, MatTableModule, MatButtonModule, MatIconModule, MatCardModule, ZadatakForma],
  templateUrl: './zadaci.html',
  styleUrl: './zadaci.css',
})
export class Zadaci implements OnInit {
  
  route = inject(ActivatedRoute);
  zadatakService = inject(ZadatakService);
  
  zadaci = signal<Zadatak[]>([]);
  
  formVisible = signal(false);

  formMode = signal<'add' | 'edit' | 'delete'>('add');

  selectedZadatak = signal<Zadatak | null>(null);
  projektId = signal<number>(0);

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.projektId.set(id);

    this.zadatakService.getAll().subscribe(
      (res) => {
        this.zadaci.set(res.filter(zadatak => zadatak.id_projekta === id));
      }
    );
  }

  getStatusText(status : number): string{

    switch(status) {
      case 0: return "novo";
      case 1: return "u toku";
      case 2: return "zavrseno";
      default: return "nepoznato"
    }
  }

  getPrioritetText(prioritet : number): string{

    switch(prioritet) {
      case 0: return "nizak";
      case 1: return "srednji";
      case 2: return "visok";
      default: return "nepoznato"
    }
  }


  sortAscPrioritet() {
    const sorted = [...this.zadaci()].sort(
      (a,b) => a.prioritet_zadatka - b.prioritet_zadatka
    );
    this.zadaci.set(sorted);
  }

  sortDescPrioritet() {
    const sorted = [...this.zadaci()].sort(
      (a,b) => b.prioritet_zadatka - a.prioritet_zadatka
    );
    this.zadaci.set(sorted);

  }

  sortAscStatus() {
    const sorted = [...this.zadaci()].sort(
      (a,b) => a.status_zadatka - b.status_zadatka
    );
    this.zadaci.set(sorted);
  }

  sortDescStatus() {
    const sorted = [...this.zadaci()].sort(
      (a,b) => b.status_zadatka - a.status_zadatka
    );
    this.zadaci.set(sorted);

  }

  otvoriDodavanje() {
  this.selectedZadatak.set(null);
  this.formMode.set('add');
  this.formVisible.set(true);
  }

  otvoriIzmenu(zad: Zadatak) {
  this.selectedZadatak.set(zad);
  this.formMode.set('edit');
  this.formVisible.set(true);
  }

  otvoriUklanjanje(zad: Zadatak) {
  this.selectedZadatak.set(zad);
  this.formMode.set('delete');
  this.formVisible.set(true);
  }

  zatvoriFormu() {
  this.formVisible.set(false);
  this.selectedZadatak.set(null);
  }

  onSacuvaj(data: Partial<Zadatak>) {
    if (this.formMode() === 'add') {
      this.zadatakService.createZadatak(data).subscribe(() => this.osveziListu());
    } else {
      this.zadatakService.updateZadatak(data.id!, data).subscribe(() => this.osveziListu());
    }
    this.zatvoriFormu();
}

  onObrisi(id: number) {
    this.zadatakService.deleteZadatak(id).subscribe(() => this.osveziListu());
    this.zatvoriFormu();
  }

  osveziListu() {
    this.zadatakService.getAll().subscribe(res => {
      this.zadaci.set(res.filter(z => z.id_projekta === this.projektId()));
    });
  }

}
