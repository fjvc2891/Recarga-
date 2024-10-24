export interface Recarga {
  id: number;
  operador: { nombre: string }; // Asegúrate que operador sea un objeto con 'nombre'
  cantidad: number;
  valor: number;
  vendedor: { nombre: string }; // Asegúrate que vendedor sea un objeto con 'nombre'
  fechaVenta: string; 
}
