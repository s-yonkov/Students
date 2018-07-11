import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import { map } from 'rxjs/operators';

@Injectable()
export class GetAllStudentsService {
    constructor(private http: Http) {
        console.log('PostService Initialized...');
    }

    getPosts() {
        return this.http.get('http://localhost:8080/api/student/all?dbTypes=MONGO&dbTypes=MYSQL')
        .pipe(map(res => res.json()));
    }
}
