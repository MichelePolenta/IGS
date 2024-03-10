import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Persona} from "../../Models/persona";
import {catchError, Observable, tap, throwError} from 'rxjs';
import {Router} from "@angular/router";
import { environment } from 'src/app/Environments/environment';

@Injectable({
  providedIn: 'root'
})
export class SignupService {

  constructor(private httpClient: HttpClient, private router: Router) { }

  public signup(user: Persona): Observable<any> {
	const headers = new HttpHeaders({ 'nomeComune': 'Ancona' });
		return this.httpClient.post(`${environment.apiUrl}/auth/signup`,
		user, {observe: 'response', headers: headers, responseType:'text'})
			.pipe(tap((response: HttpResponse<any>) => {
				console.log(response);
					if(response.status === 200) {
						this.router.navigate(['/login']);
					}
				}),
				catchError(error => {
					if (error.status === 400) {
						return throwError('I dati inseriti sono non validi: ' + error.error);
					  } else {
						return throwError('Errore imprevisto durante la registrazione.');
					  }
				})
			);
	}
}
