import { Component, inject, OnInit } from '@angular/core';
import { EstudianteService } from '../../Service/estudiante-service';
import { Estudiante } from '../../Model/estudiante';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-estudiante-form-component',
  imports: [CommonModule,FormsModule],
  standalone: true,
  templateUrl: './estudiante-form-component.html',
  styleUrl: './estudiante-form-component.css'
})
export class EstudianteFormComponent implements OnInit{
  estudiante:Estudiante=new Estudiante();
  private service=inject(EstudianteService);
  private router=inject(Router);
  private route=inject(ActivatedRoute);
  
 ngOnInit(): void {
    this.route.paramMap.subscribe(parametro => {
      const id = parametro.get('id');
      if (id) { 
        this.service.findById(+id).subscribe(est => {
          this.estudiante = est;
        });
      }
    });
  }
  

  create(){
    this.service.create(this.estudiante).subscribe(x=>{
      console.log("SE CREO UN NUEVO STUDIANTE"+x.idEstudiante);
      this.router.navigate(['/'])
    })
  }
  goHome(){
    this.router.navigate(["/"]);
  }

}
