import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from 'src/app/Services/authentication/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loginForm: FormGroup;
  hidePassword = true;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthenticationService, // Inserisci il tuo servizio di autenticazione
    private router: Router
  ) {
    this.loginForm = this.formBuilder.group({
      mail: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  togglePasswordVisibility(){
    this.hidePassword = !this.hidePassword;
  }

  login() {
    if (this.loginForm.valid) {
      const mailControl = this.loginForm.get('mail')!.value;
      const passwordControl = this.loginForm.get('password')!.value;

        this.authService.login({"mail" : mailControl, "password": passwordControl}).subscribe(
          (response: any) => {
            // Login riuscito, gestisci la risposta se necessario
            console.log('Login riuscito:', response);

            // Naviga verso la tua pagina home o altrove
              window.location.assign("/home"); 
          },
          (error: any) => {
            // Login fallito, gestisci l'errore se necessario
            console.error('Login fallito:', error);
          }
        );
      }
    }
  }
