import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EstudianteListComponent } from './Component/estudiante-list.component/estudiante-list.component';
import { EstudianteFormComponent } from "./Component/estudiante-form-component/estudiante-form-component";

@Component({
  selector: 'app-root',
  imports: [EstudianteListComponent, EstudianteFormComponent, RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('FullStack_Front');
}
