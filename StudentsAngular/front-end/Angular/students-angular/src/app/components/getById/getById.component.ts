import { Component } from '@angular/core';
import { GetStudentService } from '../../services/getAll.service';
import * as $ from 'jquery';
import { initChangeDetectorIfExisting } from '../../../../node_modules/@angular/core/src/render3/instructions';


@Component({
  selector: 'app-get-by-id',
  templateUrl: './getById.components.html',
  providers: [GetStudentService]
})
export class GetStudentByIdComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: Student[] = [];
  mysqlOutput: Student[] = [];
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;
  path: string;
  readonly url = 'http://localhost:8080/api/student/';
  id: string;
  dbUrl: string;
  mongoErrMsg: string;
  mysqlErrMsg: string;
  hasMongoErr: boolean;
  hasMysqlErr: boolean;

  constructor(private get: GetStudentService) {
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
        case 'NO_SUCH_ID': {
          that.initErrOutput(dbResponse, 'No such ID');
          break;
        }
        case 'INVALID_DB': {
          that.initErrOutput(dbResponse, 'Invalid Database type selected');
          break;
        }
        default: {
          console.log('Problem in the switch case in the responses');
          break;
        }
      }
    });
  }

  initSuccessOutput(dbResponse) {
    switch (dbResponse.dbType) {
      case 'MONGO':
        this.hasMongoErr = false;
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
    if (this.validateCheckbox() && this.validateId()) {
      this.initResult();
      this.showResult = true;
    } else {
      this.showResult = false;
    }
  }

  validateCheckbox() {
    this.checkboxIsChecked = $('.custom-control-input:checked:checked').length;

    if (!this.checkboxIsChecked) {
      alert('You must check at least one checkbox.');
      return false;
    } else {
      this.initChecboxValues();
      return true;
    }
  }

  validateId() {

    if ($('#searchId').val() == null || $('#searchId').val() === ''
      || $('#searchId').length === 0) {
      alert('Please insert ID');
      return false;
    } else {
      return true;
    }
  }

  constructPath() {
    this.id = this.constructID();
    this.dbUrl = this.constructDbUrl();
    this.path = this.url + this.id + '?' + this.dbUrl;
  }

  initResult() {
    this.constructPath();

    this.get.getPosts(this.path).subscribe((result) => {
      this.dbResponses = result.dbResponses;
      this.initValues();
    });

  }

  constructDbUrl() {
    const dbTypes = [];
    $('.custom-control-input:checked').each(function () {
      dbTypes.push('dbTypes=' + $(this).val().toString().toUpperCase() + '&');
    });

    dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
      .slice(0, -1);

    const result: string = dbTypes.join('');
    console.log(result);
    return result;
  }

  constructID() {
    const result: string = $('#searchId').val().toString();

    return result;
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
