import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Router} from '@angular/router';
import {Lesson} from '../models/lesson.model';
import {UserValidationErrors} from '../models/user-validation-errors.model';
import {LessonValidationErrors} from '../models/lesson-validation-errors.model';

@Component({
  selector: 'app-offer-a-lesson',
  templateUrl: './offer-a-lesson.component.html',
  styleUrls: ['./offer-a-lesson.component.css']
})
export class OfferALessonComponent implements OnInit {
  subject: string;
  date: object;
  time: string;
  duration: number;
  price: number;
  offerLessonUrl = 'http://localhost:8080/api/lessons';
  submitted = false;
  validationErrors: LessonValidationErrors = {};

  constructor(private httpClient: HttpClient, private router: Router) {
  }

  ngOnInit(): void {
  }

  offerALesson(): void {
    const newLesson = new Lesson(this.subject, this.date, this.time, this.duration, this.price);
    this.httpClient.post(this.offerLessonUrl, newLesson)
      .subscribe(
        () => {
          this.submitted = true;
          alert('Lesson succesfully offered.');
          this.router.navigate(['offer-a-lesson']).then(() => location.reload());
        },
        errorResponse => {
          this.submitted = true;
          this.validationErrors = errorResponse.error;
        }
      );
  }

}
