import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { routes } from './app.routes';
import { RouterModule, provideRouter } from '@angular/router';

import { AppComponent } from './app.component';
import { MapComponent } from './Components/map/map.component';
import { ContattiComponent} from './Components/contatti/contatti.component';
import { ProfiloComponent } from './Components/profilo/profilo.component';
import { LoginComponent } from './Components/login/login.component';

import { environment } from './Environments/environment';
import { HeaderInterceptor } from './Services/header/header-interceptor';



import { HttpClientModule, HTTP_INTERCEPTORS, provideHttpClient } from '@angular/common/http';
import { JwtModule } from '@auth0/angular-jwt'; 



import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { MatButtonModule} from '@angular/material/button';
import { MatSelectModule} from '@angular/material/select';
import { MatInputModule} from '@angular/material/input';
import { MatFormFieldModule, MatLabel} from '@angular/material/form-field';
import { MatDividerModule} from '@angular/material/divider';
import { MatListModule} from '@angular/material/list';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule} from '@angular/material/datepicker';
import { MatStepperModule} from '@angular/material/stepper';
import { MatRadioModule} from '@angular/material/radio';
import { FormsModule} from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import { MatNativeDateModule } from '@angular/material/core';


import { MatSidenavModule} from '@angular/material/sidenav';
import { MatToolbarModule} from '@angular/material/toolbar';
import { MatGridListModule} from '@angular/material/grid-list';
import { MatDialogModule} from '@angular/material/dialog';
import { MatMenuModule } from '@angular/material/menu';
import { MatTabsModule} from '@angular/material/tabs';
import {MatTableModule} from '@angular/material/table';
import {MatButtonToggleModule} from '@angular/material/button-toggle';
import { MatPaginatorModule } from '@angular/material/paginator';
import {MatAutocompleteModule} from '@angular/material/autocomplete';
import { WorkareaComponent } from './Components/workarea/workarea.component';
import  {MatTabGroup}  from '@angular/material/tabs';
import { SignUpComponent } from './Components/sign-up/sign-up.component';



@NgModule({
  declarations: [
    AppComponent,
    MapComponent,
    LoginComponent,
    ContattiComponent,
    ProfiloComponent,
    SignUpComponent,
    WorkareaComponent,
  ],
  imports: [
    RouterModule.forRoot(routes),  
    MatSidenavModule,
    MatToolbarModule,
    MatAutocompleteModule,
    HttpClientModule,   
    MatButtonToggleModule,
    MatPaginatorModule,
    MatTabsModule,
    MatTableModule,
    MatMenuModule,
    MatGridListModule,
    MatStepperModule,
    MatDialogModule,
    MatNativeDateModule,
    ReactiveFormsModule,
    MatRadioModule,
    FormsModule,
    MatDatepickerModule,
    MatIconModule,
    MatCardModule,
    MatFormFieldModule,
    MatDividerModule,
    MatListModule,
    MatInputModule,
    MatSelectModule,
    MatButtonModule,
    BrowserModule,
    NgbModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatButtonToggleModule,



    JwtModule.forRoot({
			config: {
				tokenGetter: () => {
					return localStorage.getItem('access_token');
				},
				allowedDomains: [environment.apiUrl],
				disallowedRoutes: [`${environment.apiUrl}/auth/login`]
			},
		}),
  ],
  exports:[
    RouterModule,
  ],
  providers: [provideHttpClient(), provideRouter(routes), {
		provide: HTTP_INTERCEPTORS,
		useClass: HeaderInterceptor,
		multi: true
	}],
  bootstrap: [AppComponent]
})
export class AppModule { }
