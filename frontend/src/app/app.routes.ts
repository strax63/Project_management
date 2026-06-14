import { Routes } from '@angular/router';
import { Login } from './pages/login/login';
import { Projekti } from './pages/projekti/projekti';
import { ProjektiDetails } from './pages/projekti-details/projekti-details';
import { authGuard } from './auth-guard';
import { Zadaci } from './pages/zadaci/zadaci';

export const routes: Routes = [
    {
        path: '',
        redirectTo: 'login',
        pathMatch: 'full'
    },
    {
        path: 'login',
        component: Login
    },
    {
        path: 'projekti',
        component: Projekti,
        canActivate: [authGuard]
    },
    {
        path: 'projekti/:id',
        component: ProjektiDetails,
        canActivate: [authGuard]
    },
    {
        path: 'projekti/:id/zadaci',
        component: Zadaci,
        canActivate: [authGuard]
    }
];
