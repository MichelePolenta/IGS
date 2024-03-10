import { Comuni } from "./comuni";
import { Poi } from "./poi";

export class Itinerario{

    id?: number;
    titolo: String;
    descrizione: String;
    comune: Comuni;
    poi: Poi[];
    visible: boolean;

    constructor(comune: Comuni, titolo: String, descrizione: String, poi: Poi[]){
        this.comune = comune,
        this.titolo = titolo,
        this.descrizione = descrizione;
        this.poi = poi;
        this.visible = false;
    }

}