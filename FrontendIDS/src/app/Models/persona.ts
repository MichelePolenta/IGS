import { Comuni } from "./comuni";
import { Roulo } from "./ruolo";

export interface Persona{
    nome: string;
    cognome: string;
    mail: string;
    password: string;
    comune: string;
    dataDiNascita : string;
    ruolo: string;
}