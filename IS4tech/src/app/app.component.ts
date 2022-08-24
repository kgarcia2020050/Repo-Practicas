import { Component } from '@angular/core';
import { ServicioService } from './services/servicio.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [ServicioService],
})
export class AppComponent {
  public title;

  constructor(private _servicio: ServicioService) {}

  ngOnInit() {

  }
}
