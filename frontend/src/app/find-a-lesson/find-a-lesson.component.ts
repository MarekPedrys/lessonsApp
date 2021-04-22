import { Component, OnInit } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {LessonDTO} from '../models/lesson-DTO.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-find-a-lesson',
  templateUrl: './find-a-lesson.component.html',
  styleUrls: ['./find-a-lesson.component.css']
})
export class FindALessonComponent implements OnInit {
  loggedUserRole = localStorage.getItem('loggedUserRole');
  private url = 'http://localhost:8080/lesson';
  lessons: LessonDTO[] = [];

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.httpClient.get<LessonDTO[]>(this.url)
      .subscribe(lessons => this.lessons = lessons);
  }

  // needs to be corrected (you can order a lesson that has already been ordered) !!!
  orderLesson(id: number): void {
    this.httpClient.post(this.url + '/' + id, {})
      .subscribe(
        () => {
          alert('Lesson successfully ordered.');
          this.router.navigate(['my-lessons']);
        }
      );
  }

}
