import { Routes } from '@angular/router';
import { EstudianteListComponent } from './Component/estudiante-list.component/estudiante-list.component';
import { EstudianteFormComponent } from './Component/estudiante-form-component/estudiante-form-component';

export const routes: Routes = [
    {path:"" , component:EstudianteListComponent},
    {path:"formularioEstudiante" , component:EstudianteFormComponent}
];
