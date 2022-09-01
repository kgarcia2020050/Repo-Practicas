export interface UserDialog {
  name: string;
  email: string;
  profile: number;
  empresas: Empresa[];
}

export interface Empresa {
  enterpriseId: number;
}
