export interface Aircraft {
  id?: number;
  marca: string;
  nome: string;
  descricao?: string;
  createdAt?: Date;
  updatedAt?: Date;
  ano: number;
  vendido: boolean;
}
