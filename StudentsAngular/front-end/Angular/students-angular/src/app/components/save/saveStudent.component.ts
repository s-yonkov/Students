import { PostStudentService } from './../../services/postStudent.service';
import { Component } from '@angular/core';
import * as $ from 'jquery';
import { Student } from '../../student.model';

@Component({
  selector: 'app-save',
  templateUrl: './saveStudent.component.html',
  providers: [PostStudentService]
})
export class SaveStudentComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: Student[] = [];
  mysqlOutput: Student[] = [];
  path: string;
  input: Input = Object();
  readonly url = 'http://localhost:8080/api/student/save';
  mongoErrMsg: string;
  mysqlErrMsg: string;
  hasMongoErr: boolean;
  hasMysqlErr: boolean;
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;
  student = new Student();


  constructor(private postStudent: PostStudentService) {
  }

  saveStudent() {
    if (this.validateCheckbox()) {
      this.initInput();
      this.postRequest();
      this.showResult = true;
      this.clearForm();
    }
  }

  initInput() {
    this.input.dbTypes = this.initDbTypes();
    this.input.student = this.student;
  }

  initDbTypes() {
    const dbTypes = [];
    $('.custom-control-input:checked').each(function () {
      dbTypes.push($(this).val());
    });
    return dbTypes;
  }

  initResponse() {
    const that = this;
    // Clear values before each call.
    this.clearValues();

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
          this.mongoOutput.push(student);
        });
        break;
      case 'MYSQL':
        dbResponse.students.students.forEach(student => {
          this.mysqlOutput.push(student);
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
        this.hasMongoErr = true;
        this.mongoErrMsg = message;
        break;
      case 'MYSQL':
        this.hasMysqlErr = true;
        this.mysqlErrMsg = message;
        break;
      default:
        console.log('Invalid Db in initErrOutput');
        break;
    }
  }

  validateCheckbox() {
    this.checkboxIsChecked = $('.custom-control-input:checked').length;

    if (!this.checkboxIsChecked) {
      alert('You must check at least one checkbox.');
      return false;
    } else {
      this.initChecboxValues();
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
    $('.custom-control-input:checked').each(function () {
      ($(this).prop('checked', false));
    });
    $('#id').val('');
    $('#name').val('');
    $('#age').val('');
    $('#grade').val('');
  }

  clearValues() {
    this.mongoOutput = [];
    this.mysqlOutput = [];
    this.hasMongoErr = false;
    this.hasMysqlErr = false;
    this.mongoErrMsg = '';
    this.mysqlErrMsg = '';
    this.student = new Student();
  }

  initChecboxValues() {
    this.mongoIsChecked = false;
    this.mysqlIsChecked = false;
    const that = this;
    $('.custom-control-input:checked:checked').each(function () {
      if ($(this).val().toString().toUpperCase() === 'MONGO') {
        that.mongoIsChecked = true;
      } else if ($(this).val().toString().toUpperCase() === 'MYSQL') {
        that.mysqlIsChecked = true;
      }
    });
  }
}

interface DbResponse {
  dbType: string;
  state: string;
  students: Students;
}

interface Students {
  students: Student[];
}

interface Input {
  dbTypes: string[];
  student: Student;
}
