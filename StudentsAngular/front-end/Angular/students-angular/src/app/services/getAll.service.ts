import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';
import { Observable, Subscribable } from '../../../node_modules/rxjs';

@Injectable()
export class GetStudentService {

    result;

    constructor(private http: Http) {
        console.log('PostService Initialized...');
    }

    getPosts(path: string) {
        this.result = this.http.get(path).pipe(map(res => res.json()));

        return this.result;
    }
}
