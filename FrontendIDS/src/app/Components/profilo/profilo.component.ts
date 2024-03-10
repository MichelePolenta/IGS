import { Component, OnInit } from '@angular/core';
import { Persona } from 'src/app/Models/persona';
import { AuthenticationService } from 'src/app/Services/authentication/authentication.service';
import { BackendService } from 'src/app/Services/backend/backendService';
@Component({
  selector: 'app-profilo',
  templateUrl: './profilo.component.html',
  styleUrls: ['./profilo.component.scss']

})
export class ProfiloComponent implements OnInit {

  p!: Persona | null;

  constructor(private backEndService: BackendService, private authenticationService: AuthenticationService) { }


  ngOnInit(): void {

    const userInfo = this.authenticationService.getUserInfo()?.mail;
    if (userInfo) {
      this.backEndService.getPersona(userInfo).subscribe(
        (persona: Persona) => {
          this.p = persona;
        },
        (errore) => {
          console.error('Errore nel recupero dei dati della persona', errore);
        }
      );
    }
}}
