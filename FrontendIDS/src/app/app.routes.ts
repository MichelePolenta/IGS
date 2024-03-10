import {Routes} from "@angular/router"
import { ContattiComponent } from "./Components/contatti/contatti.component"
import { ProfiloComponent } from "./Components/profilo/profilo.component"
import { LoginComponent } from "./Components/login/login.component"
import { MapComponent } from "./Components/map/map.component"
import { WorkareaComponent } from "./Components/workarea/workarea.component"
import { SignUpComponent } from "./Components/sign-up/sign-up.component"

export const routes: Routes = [
	{path: "", redirectTo: "home", pathMatch: "full"},
	{path: "home", component: MapComponent},
    {path: "contact", component: ContattiComponent},
	{path: "profile", component: ProfiloComponent},
	{path: "workarea", component: WorkareaComponent},
	{path: "login", component: LoginComponent},
	{path: "signup", component: SignUpComponent},



]