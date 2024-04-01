import { GastoPorCategoria } from './gasto-por-categoria';

export interface Resumo {
  totalReceitas: number;
  totalDespesas: number;
  saldo: number;
  gastoPorCategoria: GastoPorCategoria[];
}
