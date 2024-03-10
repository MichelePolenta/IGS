import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {JwtHelperService} from '@auth0/angular-jwt';
import {catchError, Observable, tap, throwError} from 'rxjs';
import {Router} from "@angular/router";
import { environment } from 'src/app/Environments/environment';
import { Persona } from 'src/app/Models/persona';


@Injectable({
	providedIn: 'root'
})
export class AuthenticationService {

	public authenticated: boolean = false;

	constructor(private httpClient: HttpClient, private router: Router, private jwtHelper: JwtHelperService) {
		const token = localStorage.getItem('access_token');
		if(token != null && !this.jwtHelper.isTokenExpired(token)) {
			this.authenticated = true;
		} else {
			this.authenticated = false;
		}
	}


	login(credentials: { mail: string; password: string }): Observable<any> {
		return this.httpClient.post(`${environment.apiUrl}/auth/login`, credentials, { observe: 'response' })
		  .pipe(
			tap((response: HttpResponse<any>) => {
			  const authToken : string | null = this.extractAuthToken(response.headers);
			  if (authToken) {
				this.storeToken(authToken);
			  }
			  const personaInfo = JSON.stringify(response.body);
			  localStorage.setItem('persona', personaInfo);
			  this.authenticated = true;
			}),
			catchError(error => {
			  console.error('Login failed. Error:', error);
			  return throwError(() => error);
			})
		  );
	}

	signup(userDetails: { mail: string; password: string }): Observable<any> {
		return this.httpClient.post(`${environment.apiUrl}api/auth/signup`, userDetails, { observe: 'response' })
		  .pipe(
			tap((response: HttpResponse<any>) => {
			  const authToken = response.headers.get('Authorization');
			  if (authToken) {
				this.storeToken(authToken);
			  }
			  const personaInfo = JSON.stringify(response.body);
			  localStorage.setItem('persona', personaInfo);
			  this.authenticated = true;
			}),
			catchError(error => {
			  console.error('Signup failed. Error:', error);
			  return throwError(() => error);
			})
		  );
	}

	public logout(): void {
		localStorage.removeItem("access_token");
		localStorage.removeItem("user-info")
		this.router.navigate(['/home']);
		this.authenticated = false;
	}

	public getUserInfo() : Persona | null {
		const userInfo = localStorage.getItem("persona");
			return JSON.parse(userInfo!);
	}

	private storeToken(token: string): void {
		localStorage.setItem('access_token', token);
	}

	private extractAuthToken(headers: HttpHeaders): string | null {
		const authHeader = headers.get('Authorization');
		if (authHeader && authHeader.startsWith('Bearer ')) {
			return authHeader.substring(7);
		}
		return null;
	}
}