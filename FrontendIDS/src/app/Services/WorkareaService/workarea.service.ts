import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Poi } from 'src/app/Models/poi';
import { environment } from 'src/app/Environments/environment';

@Injectable({
  providedIn: 'root'
})




export class WorkareaService {

  constructor(private http: HttpClient) { }

  inserisciPoiFisico(comune: string, puntoFisico: Poi): Observable<any> {
    return this.http.post(`${environment.apiUrl}/contributoraut/inserisciPoiFisico/${comune}`, puntoFisico)
    .pipe(
      catchError((error: HttpErrorResponse) => {
        if (error.status === 400) {
          return throwError('Errore di validazione: ' + error.error);
        } else if (error.status === 404) {
          return throwError('Il punto non è interno al comune specificato.');
        } else {
          return throwError('Si è verificato un errore durante l\'inserimento del punto fisico.');
        }
      })
    );
  }


  eliminaPoiFisico(id : number){
    return this.http.delete(`${environment.apiUrl}/contributoraut/eliminaPoi/${id}`);
  }

  modificaPoiFisico( id : number, poi : Poi){
    return this.http.put(`${environment.apiUrl}/contributoraut/modificaPuntoFisico/${id}`, poi)
  }


  private handleError(error: any) {
    console.error('Si è verificato un errore:', error);
    return throwError(error);
  }
}

