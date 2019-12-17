import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CompraComponent } from './compra/compra.component';
import { FacturaComponent } from './factura/factura.component';
import { AppComponent } from './app.component';
import { ComprasComponent } from './compras/compras.component';

const routes: Routes = [
  { path: 'eventos', component: CompraComponent },
  { path: 'factura/:id', component: FacturaComponent },
  { path: 'factura', component: FacturaComponent },
  { path: 'compras', component: ComprasComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
