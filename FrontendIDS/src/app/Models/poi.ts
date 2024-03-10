
export class Poi {

    jsonType : string = "PuntoFisico";
    titolo : string;
    descrizione! : string;
    latitudine! : number;
    longitudine! : number;
    id! : number;
    constructor(titolo : string, descrizione : string, latitudine : number, longitudine : number){
        this.titolo = titolo;
        this.descrizione = descrizione;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    static createFromJson(data : Poi) : Poi{
      return new Poi(data.titolo, data.descrizione, data.latitudine, data.longitudine);
    }

    getNome() :  string{
      return this.titolo;
    }

    getDescrizione() : string{
      return this.descrizione;
    }

    getLatitudine() : number{
      return this.latitudine;
    }

    getLongitudine() : number{
      return this.longitudine;
    }

    getId() : number{
      return this.id;
    }

    setNome(titolo : string) : void{
      this.titolo = titolo;
    }

    setDescrizione(descrizione : string) : void{
      this.descrizione = descrizione;
    }

    setLatitudine(latitudine : number) : void{
      this.latitudine = latitudine;
    }

    setLongitudine(longitudine : number) : void{
      this.longitudine = longitudine;
    }
    setId(id : number) : void{
      this.id = id;
    }


}
