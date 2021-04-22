import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FindALessonComponent } from './find-a-lesson.component';

describe('FindALessonComponent', () => {
  let component: FindALessonComponent;
  let fixture: ComponentFixture<FindALessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FindALessonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FindALessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
