import { Component, inject, OnInit } from '@angular/core';
import { EstudianteService } from '../../Service/estudiante-service';
import { Estudiante } from '../../Model/estudiante';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-estudiante-form-component',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './estudiante-form-component.html',
  styleUrl: './estudiante-form-component.css'
})
export class EstudianteFormComponent {
  
  estudiante:Estudiante=new Estudiante();
  private service=inject(EstudianteService);

  create(){
    this.service.create(this.estudiante).subscribe(x=>{
      console.log("SE CREO UN NUEVO STUDIANTE"+x.idEstudiante);
    })
  }
}
