import { Marca } from './marca';
import { Decade } from './decade';

export interface Dashboard {
  unsold: number;
  registeredLastWeek: number;
  aircraftByMarca: Marca[];
  aircraftByDecade: Decade[] | null;
}
