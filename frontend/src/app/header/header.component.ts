import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {LoggedUser} from '../models/logged-user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  loggedUserRole = localStorage.getItem('loggedUserRole');
  loggedUserName = localStorage.getItem('loggedUserName');
  loggedUserHasNewOrder = localStorage.getItem('loggedUserHasNewOrder');
  loggedUserPhoto = localStorage.getItem('loggedUserPhoto');

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    setInterval(() => this.checkIfHasNewOrder(), 2000);
  }

  // window.location.reload() needs to be replaced...
  checkIfHasNewOrder(): void {
    if (this.loggedUserHasNewOrder === 'false') {
      this.httpClient.post<LoggedUser>('http://localhost:8080/user/login', {})
        .subscribe(loggedUser => {
          if (String(loggedUser.hasNewOrder) === 'true') {
            localStorage.setItem('loggedUserHasNewOrder', 'true');
            window.location.reload();
          }
        });
    }
  }

  logout(): void {
    localStorage.removeItem('authorizationHeader');
    // localStorage.removeItem('loggedUser');
    localStorage.removeItem('loggedUserName');
    localStorage.removeItem('loggedUserRole');
    localStorage.removeItem('loggedUserHasNewOrder');
    localStorage.removeItem('loggedUserPhoto');
  }

  hideNewOrderBadge(): void {
    this.httpClient.post('http://localhost:8080/user/hideNewOrderBadge', {})
      .subscribe();
    localStorage.setItem('loggedUserHasNewOrder', 'false');
  }

}
