import { Component } from '@angular/core';
import { GetAllStudentsService } from '../../services/getAll.service';
import * as $ from 'jquery';
import { initChangeDetectorIfExisting } from '../../../../node_modules/@angular/core/src/render3/instructions';


@Component({
  selector: 'app-get-by-id',
  templateUrl: './getById.components.html',
  providers: [GetAllStudentsService]
})
export class GetStudentByIdComponent {

  dbResponses: DbResponse[];
  checkboxIsChecked: number;
  showResult: boolean;
  mongoOutput: String[] = [];
  mysqlOutput: String[] = [];
  mongoIsChecked: boolean;
  mysqlIsChecked: boolean;
  path: string;
  readonly url = 'http://localhost:8080/api/student/';
  id: string;
  dbUrl: string;

  constructor(private get: GetAllStudentsService) {
  }

  initValues() {
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
    if (this.validateCheckbox() && this.validateId()) {
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
    $('.dbType:checked').each(function () {
      dbTypes.push('dbTypes=' + $(this).val().toString().toUpperCase() + '&');
    });

    dbTypes[dbTypes.length - 1] = dbTypes[dbTypes.length - 1]
      .slice(0, -1);

    const result: string = dbTypes.join('');

    return result;
  }

  constructID() {
    const result: string = $('#searchId').val().toString();

    return result;
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
