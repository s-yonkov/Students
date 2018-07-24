import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable, Subscribable } from '../../../node_modules/rxjs';

@Injectable()
export class PostStudentService {

    result;

    constructor(private http: Http) {
        console.log('PostService Initialized...');
    }
    postStudent(path: string, input: Input) {
        this.result = this.http.post(path, input).pipe(map(res => res.json()));

        return this.result;
    }
}
interface Input {
    dbTypes: string[];
    student: Student;
}
interface Student {
    id: number;
    name: string;
    age: number;
    grade: number;
}
