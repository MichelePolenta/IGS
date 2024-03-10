import { Component } from '@angular/core';
import { Persona } from './Models/persona';
import { BackendService } from './Services/backend/backendService';
import { AuthenticationService } from './Services/authentication/authentication.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  opened= false;
  title: any;
  user!: Persona;
  auth: boolean = this.authenticationService.authenticated;

  constructor(private backEndService: BackendService, private authenticationService: AuthenticationService) {
  }

  refresh(url:string){
      window.location.assign(url);
  }

  ngOnInit(){
    const userInfo = this.authenticationService.getUserInfo()?.mail;
    if (userInfo && this.auth) {
      this.backEndService.getPersona(userInfo).subscribe(
        (persona: Persona) => {
          this.user = persona;
        },
        (errore) => {
          console.error('Errore nel recupero dei dati della persona', errore);
        }
      );
    }
  }

  logout(s: string) {
    this.authenticationService.logout();
    this.refresh(s);
  }
}
