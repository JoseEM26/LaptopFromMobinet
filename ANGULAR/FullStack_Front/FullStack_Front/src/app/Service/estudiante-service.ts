// src/app/service/estudiante.service.ts
import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Estudiante } from '../Model/estudiante';

@Injectable({
  providedIn: 'root'
})
export class EstudianteService {
  private baseUrl = 'http://localhost:8080/estudiante';

    private http=inject(HttpClient);


  listarEstudiantes(){
    return this.http.get<Estudiante[]>(this.baseUrl)
  }

  create(estudiante:Estudiante){  
    console.log(this.baseUrl+"/registrar");
    return this.http.post<Estudiante>(this.baseUrl+"/registrar",estudiante)
  }

  update(estudiante:Estudiante){
    return this.http.put<Estudiante>(this.baseUrl+"/registrar",estudiante)
  }

  delete(id:number){
    return this.http.delete<Estudiante>(this.baseUrl+"/eliminar/"+id)
  }

  findById(id:number){
    return this.http.get<Estudiante>(this.baseUrl+"/listar/"+id)
  }

}
