import { Component } from '@angular/core';
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

  constructor(private getAll: GetAllStudentsService) {

    this.getAll.getPosts().subscribe((result) => {
    this.dbResponses = result.dbResponses;
    });
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
    status: string;
    students: Student[];
}

interface Student {
    id: number;
    name: string;
    age: number;
    grade: number;
}


