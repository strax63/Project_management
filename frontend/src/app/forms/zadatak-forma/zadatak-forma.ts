import { Component, EventEmitter, inject, input, Input, OnChanges, output, Output, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { Zadatak } from '../../model/zadatak';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-zadatak-forma',
  imports: [ReactiveFormsModule, MatInputModule, MatSelectModule, MatButtonModule, MatFormFieldModule, MatIconModule],
  templateUrl: './zadatak-forma.html',
  styleUrl: './zadatak-forma.css',
})
export class ZadatakForma implements OnChanges{

  zadatak = input<Zadatak | null>(null);
  mode = input<'add' | 'edit' | 'delete'>('add');
  idProjekta = input<number>(0);

  sacuvaj = output<Partial<Zadatak>>();
  obrisi = output<number>();
  otkazi = output<void>();

  fb = inject(FormBuilder);

  forma: FormGroup = this.fb.group({
    opis_zadatka: ['', [Validators.required, Validators.minLength(3)]],
    status_zadatka: [0, Validators.required],
    prioritet_zadatka: [0, Validators.required],
  });

  ngOnChanges(changes: SimpleChanges): void {
    const zad = this.zadatak();
    if(zad){
      this.forma.setValue({
        opis_zadatka: zad.opis_zadatka,
        status_zadatka: zad.status_zadatka,
        prioritet_zadatka: zad.prioritet_zadatka,
      });
    } else {
      this.forma.reset({ opis_zadatka: '', status_zadatka: 0, prioritet_zadatka: 0 });
    }
  }

  onSacuvaj() {
    if (this.forma.invalid) return;
    const zad = this.zadatak();
    const data: Partial<Zadatak> = {
      ...(zad ? { id: zad.id } : {}),
      ...this.forma.value,
      id_projekta: this.idProjekta(),
    };
    this.sacuvaj.emit(data);
  }

  onObrisi() {
    const zad = this.zadatak();
    if (zad) this.obrisi.emit(zad.id);
  }

  onOtkazi() {
    this.otkazi.emit();
  }

  

}
