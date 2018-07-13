import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable, Subscribable } from '../../../node_modules/rxjs';

@Injectable()
export class GetAllStudentsService {

    result;

    constructor(private http: Http) {
        console.log('PostService Initialized...');
        this.result = this.http.get('http://localhost:8080/api/student/all?dbTypes=MONGO&dbTypes=MYSQL').pipe(map(res => res.json()));
        console.log('COnstructor end');
    }

    getPosts() {
        console.log('TEST SERVICE');
        return this.result;

    }

}
