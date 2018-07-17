import { PostStudentService } from './../../services/postStudent.service';
import { Component } from '@angular/core';
import * as $ from 'jquery';

@Component({
  selector: 'app-save',
  templateUrl: './saveStudent.component.html',
  providers: [PostStudentService]
})
export class SaveStudentComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: String[] = [];
  mysqlOutput: String[] = [];
  path: string;
  input: Input = Object();
  readonly url = 'http://localhost:8080/api/student/save';

  constructor(private postStudent: PostStudentService) {
  }

  saveStudent() {
    if (this.validateCheckbox()) {
      this.obtainInput();
      this.postRequest();
      this.showResult = true;
      this.clearForm();
    }
  }
  obtainInput() {
    this.input.dbTypes = this.initDbTypes();
    this.input.student = this.initStudent();
  }
  initStudent() {
    const student = Object();

    student.id = $('#id').val();
    student.name = $('#name').val();
    student.age = $('#age').val();
    student.grade = $('#grade').val();

    return student;
  }
  initDbTypes() {
    const dbTypes = [];
    $('.dbType:checked').each(function () {
      dbTypes.push($(this).val());
    });
    return dbTypes;
  }
  initResponse() {
    const that = this;
    // Clear arrays before each call.
    this.mongoOutput = [];
    this.mysqlOutput = [];

    this.dbResponses.forEach(function (dbResponse) {
      switch (dbResponse.state) {
        case 'SUCCESS': {
          that.initSuccessOutput(dbResponse);
          break;
        }
        case 'CONNECTION_PROBLEM': {
          that.initErrOutput(dbResponse, 'Connection problem with the Database');
          break;
        }
        default: {
          console.log('Problem in the switch case');
          break;
        }
      }
    });
  }
  initSuccessOutput(dbResponse) {
    switch (dbResponse.dbType) {
      case 'MONGO':
        dbResponse.students.students.forEach(student => {
          this.mongoOutput.push('Id: ' + student.id + ' Name: ' + student.name + ' Age: '
            + student.age + ' Grade: ' + student.grade + ' - Has been added');
        });
        break;
      case 'MYSQL':
        dbResponse.students.students.forEach(student => {
          this.mysqlOutput.push('Id: ' + student.id + ' Name: ' + student.name + ' Age: '
            + student.age + ' Grade: ' + student.grade + ' - Has been added');
        });
        break;
      default:
        console.log('Invalid Db in initSuccessOutput');
        break;
    }
  }
  initErrOutput(dbResponse: DbResponse, message: string) {
    switch (dbResponse.dbType) {
      case 'MONGO':
        this.mongoOutput.push(message);
        break;
      case 'MYSQL':
        this.mysqlOutput.push(message);
        break;
      default:
        console.log('Invalid Db in initErrOutput');
        break;
    }
  }
  validateCheckbox() {
    this.checkboxIsChecked = $('.dbType:checked').length;

    if (!this.checkboxIsChecked) {
      alert('You must check at least one checkbox.');
      return false;
    } else {
      return true;
    }
  }
  postRequest() {
    this.postStudent.postStudent(this.url, this.input).subscribe((result) => {
      this.dbResponses = result.dbResponses;
      this.initResponse();
    });
  }
  clearForm() {
    $('.dbType:checked').each(function () {
      ($(this).prop('checked', false));
    });
    $('#id').val('');
    $('#name').val('');
    $('#age').val('');
    $('#grade').val('');
  }
}
interface DbResponse {
  dbType: string;
  state: string;
  students: Students;
}
interface Student {
  id: number;
  name: string;
  age: number;
  grade: number;
}
interface Students {
  students: Student[];
}
interface Input {
  dbTypes: string[];
  student: Student;
}
