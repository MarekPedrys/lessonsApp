import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {LessonDTO} from '../models/lesson-DTO.model';
import {Router} from '@angular/router';

@Component({
  selector: 'app-find-a-lesson',
  templateUrl: './find-a-lesson.component.html',
  styleUrls: ['./find-a-lesson.component.css']
})
export class FindALessonComponent implements OnInit {
  loggedUserRole = localStorage.getItem('loggedUserRole');
  lessons: LessonDTO[] = [];
  urlAPI = 'http://localhost:8080/api';

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
    this.loadData();
  }

  private loadData(): void {
    this.httpClient.get<LessonDTO[]>(this.urlAPI + '/lessons')
      .subscribe(lessons => this.lessons = lessons);
  }

  // needs to be corrected (you can order a lesson that has already been ordered) !!!
  orderLesson(lessonId: number, teacherId: number): void {
    this.httpClient.put(this.urlAPI + '/lessons/' + lessonId, {})
      .subscribe(
        () => {
          alert('Lesson successfully ordered.');
          this.router.navigate(['my-lessons']);
        }
      );

      const headers = new HttpHeaders({'Content-Type':'application/json; charset=utf-8'});    
      this.httpClient.patch( this.urlAPI + '/users/' + teacherId, true, {headers: headers})
        .subscribe();  
  }


}
