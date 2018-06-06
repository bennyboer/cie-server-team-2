import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CourseSelectionsDialog } from './course-dialog.component';

describe('CourseSelectionsDialog', () => {
  let component: CourseSelectionsDialog;
  let fixture: ComponentFixture<CourseSelectionsDialog>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CourseSelectionsDialog ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CourseSelectionsDialog);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
