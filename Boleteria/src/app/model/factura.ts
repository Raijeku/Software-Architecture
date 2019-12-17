import { Boleta } from './boleta';

export class Factura {
    id: string;
    boleta: Boleta;
    fecha: string;
    cedulaUsuario: number;
    cantidad: number;
    valor: number;
}