
export class Poi {

    jsonType : string = "PuntoFisico";
    nome! : string;
    descrizione! : string;
    latitudine! : number;
    longitudine! : number;
    id! : number;
    constructor(nome : string, descrizione : string, latitudine : number, longitudine : number){
        this.nome = nome;
        this.descrizione = descrizione;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
    }

    static createFromJson(data : Poi) : Poi{
      return new Poi(data.nome, data.descrizione, data.latitudine, data.longitudine);
    }

    getNome() :  string{
      return this.nome;
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

    setNome(nome : string) : void{
      this.nome = nome;
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