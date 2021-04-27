import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {LoggedUser} from '../models/logged-user.model';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
  loggedUserId = localStorage.getItem('loggedUserId')
  loggedUserRole = localStorage.getItem('loggedUserRole');
  loggedUserName = localStorage.getItem('loggedUserName');
  loggedUserHasNewOrder = localStorage.getItem('loggedUserHasNewOrder');
  loggedUserPhoto = localStorage.getItem('loggedUserPhoto');

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    setInterval(() => this.checkIfHasNewOrder(), 1000);
  }

  // window.location.reload() needs to be replaced...
  checkIfHasNewOrder(): void {
    if (this.loggedUserHasNewOrder === 'false') {
      this.httpClient.get<LoggedUser>('http://localhost:8080/api/users/login', {})
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
    const bodyBoolean = false;
    this.httpClient.put('http://localhost:8080/api/users/' + this.loggedUserId, bodyBoolean)
      .subscribe();
    localStorage.setItem('loggedUserHasNewOrder', 'false');
  }

}
