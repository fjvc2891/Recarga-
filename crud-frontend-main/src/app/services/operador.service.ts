import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class OperadorService {
  private apiUrl = 'http://localhost:8080/api/operadores';  // URL del endpoint para operadores

  constructor(private http: HttpClient) {}


  getOperadores(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  } 

}
