import { Component, OnInit, ViewChild, ElementRef, Input } from '@angular/core';
import * as L from 'leaflet';

import { PoiService } from 'src/app/Services/poiService/poi.service';


@Component({
  selector: 'app-map',
  styleUrls: ['./map.component.scss'],
  templateUrl: './map.component.html'
})
export class MapComponent implements OnInit {

  items: any[] = [];
  marker: any;  
  private map: any;
  router: any;



  constructor(private poiService: PoiService) {

  }


  ngOnInit(): void {
    this.map = L.map('map').setView([43.612053, 13.515595], 11);
    
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);
  
    this.getAllPoint();
  }  

  
  private getAllPoint(){
    this.poiService.getPoiData().subscribe((poi) => {
      this.items = poi;
  
      for (const item of poi) {
        const popUpContent = `<b>${item.nome}</b><br>Id: ${item.id}<br>Descrizione: ${item.descrizione}<br>Latitudine: ${item.latitudine}<br>Longitudine: ${item.longitudine}`;
        const marker = L.marker([item.latitudine, item.longitudine], {
          draggable: false
        }).addTo(this.map)
          .bindPopup(popUpContent);
      }
    });
  }
  
}