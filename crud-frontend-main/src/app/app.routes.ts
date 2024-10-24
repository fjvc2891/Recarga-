import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { RecargaFormComponent } from './recarga-form/recarga-form.component';

export const routes: Routes = [
    {
        path:'',
        component:HomeComponent,
        title:'Página de inicio'
    },
    {
        path:'recarga-form/:id',
        component:RecargaFormComponent,
        title:'Formulario de libros'
    },
    {
        path:'**',
        redirectTo:'',
        pathMatch:'full'
    }
];
