import { Component } from '@angular/core';
import { GetStudentService } from '../../services/getAll.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-get-all-students',
  templateUrl: './getAll.components.html',
  providers: [GetStudentService]
})
export class GetAllStudentsComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: Student[] = [];
  mysqlOutput: Student[] = [];
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;
  path: string;
  readonly url = 'http://localhost:8080/api/student/all?';
  mongoErrMsg: string;
  mysqlErrMsg: string;
  hasMongoErr: boolean;
  hasMysqlErr: boolean;

  constructor(private getAll: GetStudentService) {
  }

  initValues() {
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
        case 'INVALID_DB': {
          that.initErrOutput(dbResponse, 'Invalid Database type selected');
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

  showStudents() {
    if (this.validateCheckbox()) {
      this.initResult();
      this.showResult = true;
    } else {
      this.showResult = false;
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

  constructPath() {
    const dbTypes = [];

    $('.custom-control-input:checked:checked').each(function () {
      dbTypes.push('dbTypes=' + $(this).val().toString().toUpperCase() + '&');
    });

    dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
      .slice(0, -1);

    const result: string = dbTypes.join('');

    this.path = this.url + result;
  }

  initResult() {
    this.constructPath();

    this.getAll.getPosts(this.path).subscribe((result) => {
      this.dbResponses = result.dbResponses;
      this.initValues();
    });

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

  clearValues() {
    this.mongoOutput = [];
    this.mysqlOutput = [];
    this.hasMongoErr = false;
    this.hasMysqlErr = false;
    this.mongoErrMsg = '';
    this.mysqlErrMsg = '';
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


