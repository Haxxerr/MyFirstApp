import { Component } from '@angular/core';
import { UserService } from './user.service';
import { User } from '../models/user.model';

@Component({
  selector: 'app-user-registration',
  templateUrl: './user-registration.component.html',
  styleUrls: ['./user-registration.component.css']
})
export class UserRegistrationComponent {
  newUser: User = {
    id: 0,
    email: '',
    username: '',
    password: '',
    locked: false,
    enabled: false,
    roles: []
  };

  constructor(private userService: UserService) {}

  registerUser() {
    this.userService.register(this.newUser).subscribe(
      (createdUser: User) => {
        console.log('User created successfully:', createdUser);
      },
      (error) => {
        console.error('Error creating user:', error);
      }
    );
  }
}