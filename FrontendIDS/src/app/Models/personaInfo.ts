import { Comuni } from "./comuni";
import { Roulo } from "./ruolo";

export interface PersonaInfo {
	nome: string;
	cognome: string;
	mail: string;
    password: string;
    comune: Comuni;
    dataDiNascita : Date;
    ruolo: Roulo;
}