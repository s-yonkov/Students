import { routing } from './app.routing';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';
import { AlertModule } from 'ngx-bootstrap';
import { AppComponent } from './app.component';
import { GetAllStudentsComponent } from './components/getAll/getAll.component';
import { GetStudentByIdComponent } from './components/getById/getById.component';
import { SaveStudentComponent } from './components/save/saveStudent.component';

@NgModule({
  declarations: [
    AppComponent,
    GetAllStudentsComponent,
    GetStudentByIdComponent,
    SaveStudentComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    AlertModule.forRoot(),
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
