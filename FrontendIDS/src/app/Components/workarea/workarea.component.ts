import {Component, OnInit} from '@angular/core';
import { Poi } from 'src/app/Models/poi';
import {FormControl} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import { PoiService } from 'src/app/Services/poiService/poi.service';
import { WorkareaService } from 'src/app/Services/WorkareaService/workarea.service';

@Component({
  selector: 'app-workarea',
  templateUrl: './workarea.component.html',
  styleUrls: ['./workarea.component.scss'],
  providers : [WorkareaService, PoiService]
})

/**
 * Classe che consente di rappresenare le operazioni della workarea
 */

export class WorkareaComponent implements OnInit{

  titolo: string = '';
  descrizione: string = '';
  latitudine!: number;
  longitudine!: number;
  riferimento!: number;
  isFisicoChecked = true;
  isLogicoChecked = false;
  idPoi = new FormControl();
  options : number[] = [];
  filteredOptions!: Observable<number[]>;

  constructor(private poiSerivce: PoiService, private service: WorkareaService) {}

  ngOnInit() {
    this.refresh();
  }

  /**
   * Metodo che esegue le azioni di modifica
   */
  modificaPoi() {
    if (this.controlloParametriModifica()) {
      if (this.options.includes(this.idPoi.value)) {
        const punto: Poi = new Poi(this.titolo, this.descrizione, this.latitudine, this.longitudine);
        punto.setId(this.idPoi.value);
        this.service.modificaPoiFisico(this.idPoi.value, punto).subscribe(
          (response) => {
            console.log('Risposta dal server:', response);
          },
          (error) => {
            console.error('Errore nella richiesta:', error);
          }
        );
      }else alert("Il poi selezionato non è esistente");
    } else alert("Bisogna compilare tutti i campi");
   this.reload();
  }

  /**
   * Controllo dei parametri specializzato per la modifica
   */
  controlloParametriModifica(): boolean {
    if (!this.controlloParametri()) return false;
    if (this.idPoi.value == null) return false;
    return true;
  }

  /**
   * Metodo per recuperare gli id dei poi
   */
  getPoiOptions() {
    this.poiSerivce.getIdPoi().subscribe(
      (options: number[]) => {
        this.options = options;
      },
      (error) => {
        console.error('Errore nel recupero delle opzioni Poi:', error);
      }
    );
  }

  /**
   * Metodo per aggiornare la lista degli id ed associarla
   * al componente
   */
  refresh(){
    this.getPoiOptions();
    this.filteredOptions = this.idPoi.valueChanges.pipe(
      startWith(''),
      map(value => this.filter(value || '')),
    );
  }


  /**
   * Metodo per inserire un poi
   */
  insertPoiFisico() {
    if (this.controlloParametri()) {
      const poi: Poi = new Poi(this.titolo, this.descrizione, this.latitudine, this.longitudine);
      console.log(poi);
      this.service.inserisciPoiFisico("Ancona", poi).subscribe(
        (response) => {
          this.refresh();
          console.log('Risposta dal server:', response);
        },
        (error) => {
          console.error('Errore nella richiesta:', error);
        });
    } else alert("Bisogna compilare tutti i campi");
    this.reload();
  }

  /**
   * Metodo per controllare che i paramentri non siano nulli
   */
  controlloParametri(): boolean{
    if (this.titolo == '') return false;
    if (this.descrizione == '') return false;
    if (this.latitudine == null) return false;
    if (this.longitudine == null) return false;
    return true;
  }

  /**
   * Metodo per eliminare i poi
   */
  eliminaPoi() {
      if (this.idPoi.value != null) {
        if (this.options.includes(this.idPoi.value)) {
          this.service.eliminaPoiFisico(this.idPoi.value).subscribe(
            (response) => {
              this.refresh();
              console.log('Risposta dal server:', response);
            },
            (error) => {
              console.error('Errore nella richiesta:', error);
            }
          );
        } else alert("Il poi selezionato non è esistente");
      } else alert("Bisogna inserire il punto da eliminare");
    this.reload();
  }

  reload(){
    window.location.assign("/workarea");
  }


  /**
   * Metodo per filtrare i vari elementi da far comparire nel componente
   * @param value
   * @private
   */
  private filter(value: string): number[] {
    return this.options.filter(option => option.toString().toLowerCase().includes(value));
  }



}
