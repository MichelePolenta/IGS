import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import { environment } from 'src/app/Environments/environment';
import { Persona } from 'src/app/Models/persona';

@Injectable({
	providedIn: 'root'
})
export class PersonaService {

	constructor(private httpClient: HttpClient) { }

	public getPersonaDetails(id: number): Observable<Persona> {
		return this.httpClient.get<Persona>(`${environment.apiUrl}/users/${id}`);
	}

}