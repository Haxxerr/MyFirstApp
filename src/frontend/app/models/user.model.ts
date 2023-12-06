export interface User {
    id: number;
    email: string;
    username: string;
    password: string;
    locked: boolean;
    enabled: boolean;
    roles: Role[];
  }
  
  export interface Role {
  }