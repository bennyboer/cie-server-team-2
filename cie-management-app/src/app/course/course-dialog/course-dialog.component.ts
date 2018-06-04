import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material';
import {Course} from '../course';
import {CourseStatus} from '../course-status';
import {Lecturer} from '../../lecturer/lecturer';
import {Department} from '../../department/department';
import {Campus} from '../../campus/campus';
import {CampusService} from '../../campus/campus.service';
import {DepartmentService} from '../../department/department.service';
import {LecturerService} from '../../lecturer/lecturer.service';

@Component({
  selector: 'app-course-dialog',
  templateUrl: './course-dialog.component.html',
  styleUrls: ['./course-dialog.component.css']
})
export class CourseDialogComponent implements OnInit {

  course: Course = new Course();

  lecturers: Lecturer[] = [];
  departments: Department[] = [];
  campuses: Campus[] = [];

  constructor(public dialogRef: MatDialogRef<CourseDialogComponent>,
              private campusService: CampusService,
              private departmentService: DepartmentService,
              private lecturerService: LecturerService,
              @Inject(MAT_DIALOG_DATA) public c: Course) {
    if (c !== undefined && c !== null) {
      this.course = c;
    }
  }

  ngOnInit() {
    this.campusService.getCampuses().subscribe(campuses => {
      this.campuses = campuses;

      if (this.course.location != null) {
        const c = this.campuses.find(campus => campus.id === this.course.location.id);
        if (c != null) {
          this.course.location = c;
        }
      }
    });

    this.departmentService.getDepartments().subscribe(departments => {
      this.departments = departments;

      if (this.course.department != null) {
        const d = this.departments.find(department => department.id === this.course.department.id);
        if (d != null) {
          this.course.department = d;
        }
      }
    });

    this.lecturerService.getLecturers().subscribe(lecturers => {
      this.lecturers = lecturers;

      if (this.course.lecturer != null) {
        const l = this.lecturers.find(lecturer => lecturer.id === this.course.lecturer.id);
        if (l != null) {
          this.course.lecturer = l;
        }
      }
    });
  }

  finish() {

    this.dialogRef.close(this.course);
  }

  getStatusNames(): string[] {
    const names = [];
    for (const member in CourseStatus) {
      const isValueProperty = parseInt(member, 10) >= 0;
      if (!isValueProperty) {
        names.push(member);
      }
    }

    return names;
  }

}
