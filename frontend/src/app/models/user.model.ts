export class User {
    id?: number;
    username: string;
    password: string;
    role: string;
    email: string;
    photo: string;
    hasNewOrder: boolean;
  
  
    constructor(username: string, password: string, role: string, email: string, photo: string) {
      this.username = username;
      this.password = password;
      this.role = role;
      this.email = email;
      this.photo = photo;
      this.hasNewOrder = false;
    }
  }