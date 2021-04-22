import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Router} from '@angular/router';
import {LoggedUser} from '../models/logged-user.model';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent {  
  username: string;
  password: string;
  loggedUser: LoggedUser = null;

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  onLoginFormSubmit(): void {
    this.storeAuthorizationHeader();
    this.login(() => {
      alert('Incorrect username or password.');
      this.username = '';
      this.password = '';
    });
  }

  private storeAuthorizationHeader(): void {
    const authorizationHeader = 'Basic ' + btoa(this.username + ':' + this.password);
    localStorage.setItem('authorizationHeader', authorizationHeader);
  }

  private login(errorCallback?: () => void): void {
    this.httpClient.post<LoggedUser>('http://localhost:8080/user/login', {})
      .subscribe(loggedUser => {
          this.loggedUser = loggedUser;
          localStorage.setItem('loggedUserName', loggedUser.username);
          localStorage.setItem('loggedUserRole', loggedUser.role);
          localStorage.setItem('loggedUserHasNewOrder', String(loggedUser.hasNewOrder));
          localStorage.setItem('loggedUserPhoto', String(loggedUser.photo));
          this.router.navigate(['find-a-lesson']).then(() => location.reload());
        },
        () => {
          if (errorCallback) {
            errorCallback();
          }
        });
  }




}
