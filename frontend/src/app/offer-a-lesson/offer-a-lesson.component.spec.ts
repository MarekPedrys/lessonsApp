import { ComponentFixture, TestBed } from '@angular/core/testing';

import { OfferALessonComponent } from './offer-a-lesson.component';

describe('OfferALessonComponent', () => {
  let component: OfferALessonComponent;
  let fixture: ComponentFixture<OfferALessonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ OfferALessonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(OfferALessonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
