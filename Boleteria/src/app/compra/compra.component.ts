import { Component, OnInit } from '@angular/core';
import { Boleta } from '../model/boleta';
import { AngularFireList, AngularFireDatabase } from 'angularfire2/database';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {
  
  facturasRef: AngularFireList<any>;
  boletas: Observable<any[]>;
  facturaId: any;
    constructor(private db: AngularFireDatabase, private router: Router) {
      this.facturasRef=db.list('facturas');
      this.boletas = db.list('boletas').valueChanges();
    }

  ngOnInit() {
    
  }

  addFactura(boleta: Boleta) {
    this.facturaId=this.facturasRef.push({ boletaComprada: boleta, cedulaUsuario: 11111, fecha: boleta.fecha, cantidad: 1, precio: boleta.precio, iva: 0.16, valor: boleta.precio*(1+0.16) }).key;
    this.db.object('/facturas/'+this.facturaId)
      .update({id: this.facturaId});
    this.db.object('/boletas/' + boleta.id)
      .update({ cantidad: boleta.cantidad-1});
    this.router.navigate(['/factura', { id: this.facturaId}]);
  }

}
