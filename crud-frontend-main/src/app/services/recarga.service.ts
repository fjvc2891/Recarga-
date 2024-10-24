import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recarga } from '../models/recarga';

@Injectable({
  providedIn: 'root',
})
export class RecargaService {
  private apiUrl = 'http://localhost:8080/api/recargas';

  constructor(private http: HttpClient) {}

  getRecargas(): Observable<Recarga[]> {
    return this.http.get<Recarga[]>(this.apiUrl);
  }

  getRecargaById(id: number): Observable<Recarga> {
    return this.http.get<Recarga>(`${this.apiUrl}/${id}`);
  }

  createRecarga(recarga: Recarga): Observable<Recarga> {
    // Asegúrate de enviar el objeto JSON directamente
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.post<Recarga>(this.apiUrl, recarga, { headers });
  }

  updateRecarga(recarga: Recarga): Observable<Recarga> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    return this.http.put<Recarga>(this.apiUrl, recarga, { headers });
  }

  deleteRecarga(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
