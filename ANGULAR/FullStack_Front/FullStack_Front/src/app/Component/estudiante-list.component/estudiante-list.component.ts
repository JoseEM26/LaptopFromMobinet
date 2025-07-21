// src/app/component/estudiante-list/estudiante-list.component.ts
import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EstudianteService } from '../../Service/estudiante-service';
import { Estudiante } from '../../Model/estudiante';

@Component({
  selector: 'app-estudiante-list',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './estudiante-list.component.html',
  styleUrls: ['./estudiante-list.component.css'],
})
export class EstudianteListComponent implements OnInit {
  private estudianteService = inject(EstudianteService);

  estudiantes: Estudiante[] = [];

  ngOnInit(): void {
    this.cargarEstudiantes();
  }

  cargarEstudiantes() {
    this.estudianteService.listarEstudiantes().subscribe(x=>{
      this.estudiantes=x
    })
  }

  



  }