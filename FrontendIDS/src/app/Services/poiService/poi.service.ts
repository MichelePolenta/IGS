import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/app/Environments/environment';
import { Poi } from 'src/app/Models/poi';

@Injectable({
  providedIn: 'root'
})
export class PoiService {

  constructor(private httpClient: HttpClient) {}

  getPoiData(): Observable<Poi[]> {
    return this.httpClient.get<Poi[]>(`${environment.apiUrl}/view/allPoi`);
  }

  getSinglePoi(id : number) : Observable<Poi>{
    return this.httpClient.get<Poi>(`${environment.apiUrl}/view/singlePoi/${id}`);
  }

  getIdPoi(): Observable<number[]>{
    return this.httpClient.get<number[]>(`${environment.apiUrl}/view/allIdPoi`);
  }
}
