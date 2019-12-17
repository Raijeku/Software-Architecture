import { Component, OnInit } from '@angular/core';
import { Factura } from '../model/factura';
import { switchMap } from 'rxjs/operators';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import { AngularFireDatabase, AngularFireObject } from 'angularfire2/database';

@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  facturaId: any;
  facturas: Observable<any[]>;
  infoFacturas: Factura[];

  constructor(private route: ActivatedRoute, private db: AngularFireDatabase) {
    this.facturas=db.list('facturas').valueChanges();
   }

  ngOnInit() {
    this.facturaId=this.route.snapshot.params['id'];
  }

}
