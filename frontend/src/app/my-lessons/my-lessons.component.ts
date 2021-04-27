import { Component, OnInit } from '@angular/core';
import {LessonDTO} from '../models/lesson-DTO.model';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-my-lessons',
  templateUrl: './my-lessons.component.html',
  styleUrls: ['./my-lessons.component.css']
})
export class MyLessonsComponent implements OnInit {
  lessons: LessonDTO[] = [];
  loggedUserId = localStorage.getItem('loggedUserId');
  loggedUserRole = localStorage.getItem('loggedUserRole');

  private url = 'http://localhost:8080/api/users/' + this.loggedUserId + '/lessons';

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.httpClient.get<LessonDTO[]>(this.url)
    .subscribe(lessons => this.lessons = lessons);

}
}
