import { Component, OnInit } from '@angular/core';
import {LessonDTO} from '../models/lesson-DTO.model';
import {HttpClient} from '@angular/common/http';

@Component({
  selector: 'app-my-lessons',
  templateUrl: './my-lessons.component.html',
  styleUrls: ['./my-lessons.component.css']
})
export class MyLessonsComponent implements OnInit {
  private urlTeacher = 'http://localhost:8080/lesson/byTeacher';
  private urlPupil = 'http://localhost:8080/lesson/byPupil';
  lessons: LessonDTO[] = [];
  loggedUserRole = localStorage.getItem('loggedUserRole');

  constructor(private httpClient: HttpClient) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    if (this.loggedUserRole === 'ROLE_TEACHER') {
      this.httpClient.get<LessonDTO[]>(this.urlTeacher)
        .subscribe(lessons => this.lessons = lessons);
    } else if (this.loggedUserRole === 'ROLE_PUPIL') {
      this.httpClient.get<LessonDTO[]>(this.urlPupil)
        .subscribe(lessons => this.lessons = lessons);
    }
  }

}
