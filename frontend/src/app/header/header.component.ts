import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
  urlUsers = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    setInterval(() => this.checkIfHasNewOrder(), 3000);
  }

  // window.location.reload() needs to be replaced...
  checkIfHasNewOrder(): void {

    if (this.loggedUserHasNewOrder === 'false') {
      this.httpClient.get<LoggedUser>(this.urlUsers + '/login', {})
        .subscribe(loggedUser => {
          if (String(loggedUser.hasNewOrder) === 'true') {
            localStorage.setItem('loggedUserHasNewOrder', 'true');
            window.location.reload();
          }
        }
        
        );
    }

    if (this.loggedUserHasNewOrder === 'true') {
      this.httpClient.get<LoggedUser>(this.urlUsers + '/login', {})
        .subscribe(loggedUser => {
          if (String(loggedUser.hasNewOrder) === 'false') {
            localStorage.setItem('loggedUserHasNewOrder', 'false');
            window.location.reload();
          }
        }
        
        );
    }

  }

  logout(): void {
    localStorage.removeItem('authorizationHeader');
    localStorage.removeItem('loggedUserName');
    localStorage.removeItem('loggedUserRole');
    localStorage.removeItem('loggedUserHasNewOrder');
    localStorage.removeItem('loggedUserPhoto');
  }

  hideNewOrderBadge(): void {
    localStorage.setItem('loggedUserHasNewOrder', 'false'); 
    window.location.reload;
    
    const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});    
    this.httpClient.patch( this.urlUsers + '/' + this.loggedUserId, false, {headers: headers})
      .subscribe();  
  }

}
