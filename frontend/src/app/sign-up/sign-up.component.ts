import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {User} from '../models/user.model';
import {Router} from '@angular/router';
import {UserValidationErrors} from '../models/user-validation-errors.model';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {
  username: string;
  password: string;
  role: string;
  email: string;
  photo: string;
  singUpUrl = 'http://localhost:8080/user/signup';
  validationErrors: UserValidationErrors = {};
  submitted = false;


  constructor(private httpClient: HttpClient, private router: Router) {
  }

  signUpUser(): void {
    localStorage.removeItem('authorizationHeader');
    const newUser = new User(this.username, this.password, this.role, this.email, this.photo);
    this.httpClient.post(this.singUpUrl, newUser)
      .subscribe(
        () => {
          this.submitted = true;
          alert('User succesfully added. Log in now.');
          this.router.navigate(['']);
        },
        errorResponse => {
          this.submitted = true;
          this.validationErrors = errorResponse.error;
        }
      );
  }
}