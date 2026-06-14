import { Component, inject, signal } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth-service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-login',
  imports: [MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatButtonModule
  ],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {
  router = inject(Router);
  authService = inject(AuthService);

  snackBar = inject(MatSnackBar)

  errorMessage = signal('');

  login(username: string, password: string): void{
    const uspesno = this.authService.login(username,password);

    if(uspesno){
      this.snackBar.open('Uspesna Prijava!','Zatvori',{
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      this.router.navigate(['/projekti'])
    }else{
      this.snackBar.open('Neuspesna Prijava! Pogresno korisnicko ime ili lozinka','Zatvori',{
        duration: 3000,
        horizontalPosition: 'center',
        verticalPosition: 'top'
      });
      
    }
  }
}
