import { Component, OnInit} from '@angular/core';
import * as L from 'leaflet';


import { PoiService } from 'src/app/Services/poiService/poi.service';


@Component({
  selector: 'app-map',
  styleUrls: ['./map.component.scss'],
  templateUrl: './map.component.html'
})
export class MapComponent implements OnInit {

  items: any[] = [];
  private map: any;
  router: any;



  constructor(private poiService: PoiService) {

  }

  reload(){
    location.reload();
  }


  ngOnInit(): void {
    this.map = L.map('map').setView([43.612053, 13.515595], 14);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
      attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(this.map);

    this.getAllPoint();
  }


  private getAllPoint(){
    const customIcon = L.icon({
      iconUrl: '../assets/marker-icon.png',
      iconSize: [48, 48], // dimensioni dell'icona
      iconAnchor: [24, 24], // punto in cui l'icona tocca il marker
      popupAnchor: [0, -32] // punto in cui il popup dovrebbe aprire rispetto all'icona
    });
    this.poiService.getPoiData().subscribe((poi) => {
      this.items = poi;
      for (const item of poi) {
        const popUpContent = `<b>${item.titolo}</b><br>Id: ${item.id}<br>Descrizione: ${item.descrizione}<br>Latitudine: ${item.latitudine}<br>Longitudine: ${item.longitudine}`;
        const marker = L.marker([item.latitudine, item.longitudine], {
          draggable: false,
          icon: customIcon,
        }).addTo(this.map)
          .bindPopup(popUpContent);
      }
    });
  }

}
