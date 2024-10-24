import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  
  @Injectable({
    providedIn: 'root',
  })
  export class VendedorService {
    private apiUrl = 'http://localhost:8080/api/vendedores';  // URL del endpoint para operadores
  
    constructor(private http: HttpClient) {}
  
    getVendedores(): Observable<any[]> {
        return this.http.get<any[]>(this.apiUrl);
    } 
  
  }