import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LecturerDialogComponent } from './lecturer-dialog.component';

describe('LecturerDialogComponent', () => {
  let component: LecturerDialogComponent;
  let fixture: ComponentFixture<LecturerDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LecturerDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LecturerDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
