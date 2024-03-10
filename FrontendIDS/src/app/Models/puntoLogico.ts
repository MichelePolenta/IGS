import { Comuni } from "./comuni";
import { Poi } from "./poi";

export interface puntoLogico extends Poi{
    id_poi?: number;
    comune: Comuni;
    nome: string;
    descrizione: string;
    latitudine: number;
    longitudine: number;
    visible: boolean;
}