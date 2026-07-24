import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Aircraft } from '../utils/interfaces/aircraft';
import { Dashboard } from '../utils/interfaces/dashboard';

@Injectable({
  providedIn: 'root',
})
export class AircraftService {
  private http = inject(HttpClient);

  private readonly API = 'http://localhost:8080/aeronaves';

  getAircraft(): Observable<Aircraft[]> {
    return this.http.get<Aircraft[]>(this.API);
  }

  search(term: string): Observable<Aircraft[]> {
    return this.http.get<Aircraft[]>(`${this.API}/find?term=${term}`);
  }

  dashboard(): Observable<Dashboard> {
    return this.http.get<Dashboard>(`${this.API}/dashboard`);
  }

  save(aircraft: Aircraft): Observable<Aircraft> {
    return this.http.post<Aircraft>(this.API, aircraft);
  }

  update(id: number, aircraft: Aircraft): Observable<Aircraft> {
    return this.http.put<Aircraft>(`${this.API}/${id}`, aircraft);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.API}/${id}`);
  }
}
