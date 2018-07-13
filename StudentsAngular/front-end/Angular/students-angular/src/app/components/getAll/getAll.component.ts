import { Component, OnInit } from '@angular/core';
import { GetAllStudentsService } from '../../services/getAll.service';
import * as $ from 'jquery';

@Component({
  selector: 'app-get-all-students',
  templateUrl: './getAll.components.html',
  providers: [GetAllStudentsService]
})
export class GetAllStudentsComponent implements OnInit {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: String[] = [];
  mysqlOutput: String[] = [];
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;

  constructor(private getAll: GetAllStudentsService) {
    console.log('CONSTRUCTOR');
  }

  ngOnInit() {
    console.log('CALL 1');

    this.getAll.getPosts().subscribe((result) => {
      this.dbResponses = result.dbResponses;
      console.log(this.dbResponses);
      console.log('CALL 2');
      this.initValues();
    });
  }

  initValues() {
    let that =  this;
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

  doSomething(resp: string): void {
    console.log(resp);
  }

  initSuccessOutput (dbResponse) {
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
        console.log('Invalid Db in initSuccessOutput');
        break;
    }
  }
  showStudents() {

    if (this.validateCheckbox()) {
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


