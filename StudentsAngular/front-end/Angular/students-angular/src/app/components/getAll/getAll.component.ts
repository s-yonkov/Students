import { Component } from '@angular/core';
import { GetAllStudentsService } from '../../services/getAll.service';


@Component({
  selector: 'app-get-all-students',
  templateUrl: './getAll.components.html',
  providers: [GetAllStudentsService]

})
export class GetAllStudentsComponent {

  dbResponses: DbResponse[];

  constructor(private getAll: GetAllStudentsService) {

    this.getAll.getPosts().subscribe((result) => {
    this.dbResponses = result.dbResponses;
    });
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
