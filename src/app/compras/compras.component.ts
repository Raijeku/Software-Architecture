import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { AngularFireDatabase } from 'angularfire2/database';
import { Router } from '@angular/router';
import { Factura } from '../model/factura';

@Component({
  selector: 'app-compras',
  templateUrl: './compras.component.html',
  styleUrls: ['./compras.component.css']
})
export class ComprasComponent implements OnInit {

  facturas: Observable<any[]>;

  constructor(private db: AngularFireDatabase, private router: Router) {
    this.facturas = db.list('facturas').valueChanges();
   }

  ngOnInit() {
  }

  viewFactura(facturaId: string) {
    this.router.navigate(['/factura', { id: facturaId}]);
  }

}
