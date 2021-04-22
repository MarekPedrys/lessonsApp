import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AuthorizationInterceptor} from './authorization.interceptor';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FindALessonComponent } from './find-a-lesson/find-a-lesson.component';
import { MyLessonsComponent } from './my-lessons/my-lessons.component';
import { OfferALessonComponent } from './offer-a-lesson/offer-a-lesson.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LogInComponent } from './log-in/log-in.component';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FindALessonComponent,
    MyLessonsComponent,
    OfferALessonComponent,
    SignUpComponent,
    LogInComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    AppRoutingModule
  ],
  providers: [{
    useClass: AuthorizationInterceptor,
    multi: true,
    provide: HTTP_INTERCEPTORS
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
