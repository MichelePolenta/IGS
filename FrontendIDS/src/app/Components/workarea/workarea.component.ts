import {Component, OnInit} from '@angular/core';
import { Poi } from 'src/app/Models/poi';
import {FormControl} from "@angular/forms";
import {map, Observable, startWith} from "rxjs";
import { PoiService } from 'src/app/Services/poiService/poi.service';
import { WorkareaService } from 'src/app/Services/WorkareaService/workarea.service';
import {MatSnackBar} from "@angular/material/snack-bar";
import {error} from "@angular/compiler-cli/src/transformers/util";

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

  nome: string = '';
  descrizione: string = '';
  latitudine!: number;
  longitudine!: number;
  riferimento!: number;
  isFisicoChecked = true;
  isLogicoChecked = false;
  idPoi = new FormControl();
  options : number[] = [];
  filteredOptions!: Observable<number[]>;

  constructor(private poiSerivce: PoiService, private service: WorkareaService, private snackBar : MatSnackBar) {}

  ngOnInit() {
    this.refresh();
  }

  openSnackBar(message: string, action: string) {
    this.snackBar.open(message, action);
  }


  /**
   * Metodo che esegue le azioni di modifica
   */
  modificaPoi() {
      if (this.options.includes(this.idPoi.value)) {
        let punto: Poi = new Poi(this.nome, this.descrizione, this.latitudine, this.longitudine);
        punto.setId(this.idPoi.value);
        console.log(punto);
        this.service.modificaPoiFisico(this.idPoi.value, punto).subscribe(
          (response) => {
            this.refresh();
            window.location.assign("/workarea");
            this.openSnackBar('Poi modificato', 'Chiudi');
          },
          (error) => {
            console.log(error);
            this.openSnackBar('Errore nella modifica', 'Chiudi');
          }
        );
      }else this.openSnackBar("Il poi selezionato non è esistente", 'Chiudi');
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
      const poi: Poi = new Poi(this.nome, this.descrizione, this.latitudine, this.longitudine);
      console.log(poi);
      this.service.inserisciPoiFisico("Ancona", poi).subscribe(
        (response) => {
          this.refresh();
          window.location.assign("/workarea");
          this.openSnackBar('Poi inserito correttamente', 'Chiudi');
        },
        (error) => {
          console.error('Errore nella richiesta:', error);
        });
    } else this.openSnackBar('Bisogna compilare tutti i campi', 'Chiudi');

  }

  /**
   * Metodo per controllare che i paramentri non siano nulli
   */
  controlloParametri(): boolean{
    if (this.nome == '') return false;
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
              window.location.assign("/workarea");
              this.openSnackBar('Poi eliminato', 'Chiudi');
            },
            (error) => {
              console.error('Errore nella richiesta:', error);
            }
          );
        } else this.openSnackBar('Il poi selezionato non è esistente', 'Chiudi');
      } else this.openSnackBar('Bisogna inserire il punto da eliminare', 'Chiudi')
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
