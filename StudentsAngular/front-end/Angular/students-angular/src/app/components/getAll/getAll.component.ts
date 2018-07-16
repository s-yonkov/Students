import { Component, OnInit } from '@angular/core';
import { GetAllStudentsService } from '../../services/getAll.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-get-all-students',
  templateUrl: './getAll.components.html',
  providers: [GetAllStudentsService]
})
export class GetAllStudentsComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: String[] = [];
  mysqlOutput: String[] = [];
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;
  path: string;
  readonly url = 'http://localhost:8080/api/student/all?';

  constructor(private getAll: GetAllStudentsService) {
  }

  initValues() {
    let that = this;
    // Clear arrays before each call.
    this.mongoOutput = [];
    this.mysqlOutput = [];

    this.dbResponses.forEach(function (dbResponse) {
      switch (dbResponse.state) {
        case 'SUCCESS': {
          console.log(dbResponse);
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
    console.log(dbResponse);
    switch (dbResponse.dbType) {
      case 'MONGO':
        dbResponse.students.students.forEach(student => {
          this.mongoOutput.push('Id: ' + student.id + ' Name: ' + student.name + ' Age: ' + student.age + ' Grade: ' + student.grade);
        });
        break;
      case 'MYSQL':
        dbResponse.students.students.forEach(student => {
          this.mysqlOutput.push('Id: ' + student.id + ' Name: ' + student.name + ' Age: ' + student.age + ' Grade: ' + student.grade);
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
  showStudents() {
    if (this.validateCheckbox()) {
      this.initResult();
      this.showResult = true;
    } else {
      this.showResult = false;
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

  constructPath() {
    let dbTypes = [];

    $('.dbType:checked').each(function () {
      dbTypes.push('dbTypes=' + $(this).val().toString().toUpperCase() + '&');
    });

    dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
      .slice(0, -1);

    let result: string = dbTypes.join('');

    this.path = this.url + result;
  }

  initResult() {
    this.constructPath();

    this.getAll.getPosts(this.path).subscribe((result) => {
      this.dbResponses = result.dbResponses;
      this.initValues();
    });

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


