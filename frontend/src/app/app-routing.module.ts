import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FindALessonComponent } from './find-a-lesson/find-a-lesson.component';
import { MyLessonsComponent } from './my-lessons/my-lessons.component';
import { OfferALessonComponent } from './offer-a-lesson/offer-a-lesson.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LogInComponent } from './log-in/log-in.component';

const routes: Routes = [
  {path: 'sign-up', component: SignUpComponent},
  {path: '', component: LogInComponent},
  {path: 'find-a-lesson', component: FindALessonComponent},
  {path: 'offer-a-lesson', component: OfferALessonComponent},
  {path: 'my-lessons', component: MyLessonsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
