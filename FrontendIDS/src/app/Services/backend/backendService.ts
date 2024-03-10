  // Esempio di servizio Angular
  import { Injectable } from '@angular/core';
  import { HttpClient } from '@angular/common/http';
  import { Observable } from 'rxjs';
  import { Persona } from 'src/app/Models/persona';
  import { environment } from 'src/app/Environments/environment';

  @Injectable({
    providedIn: 'root',
  })
  export class BackendService { 

    constructor(private http: HttpClient) {}

      getPersona(userMail: String): Observable<Persona> {
              return this.http.get<Persona>(`${environment.apiUrl}/users/userDetails/${userMail}`);
      }
  }
