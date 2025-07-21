import { Routes } from '@angular/router';
import { EstudianteListComponent } from './Component/estudiante-list.component/estudiante-list.component';
import { EstudianteFormComponent } from './Component/estudiante-form-component/estudiante-form-component';
import { NotFoundComponent } from './Component/not-found-component/not-found-component';

export const routes: Routes = [
    {path:"" , component:EstudianteListComponent},
    {path:"formularioEstudiante" , component:EstudianteFormComponent},
      { path: '**', component: NotFoundComponent }, // ruta comodín (404)

];
