import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Department} from './department';

@Injectable()
export class DepartmentService {

  constructor(private http: HttpClient) {
  }

  private apiUrl = '/api/departments';

  public getDepartments() {
    return this.http.get<Department[]>(this.apiUrl);
  }

  public getDepartment(id: number) {
    return this.http.get<Department>(this.apiUrl + '/' + id);
  }

  public deleteDepartment(department: Department) {
    return this.http.delete(this.apiUrl + '/' + department.id);
  }

  public createDepartment(department: Department) {
    return this.http.post(this.apiUrl, department);
  }

  public updateDepartment(department: Department) {
    return this.http.put(this.apiUrl, department);
  }

}
