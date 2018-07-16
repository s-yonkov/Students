import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  template: `
  <div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
      <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
        <li class="nav-item active">
         <a class="nav-link" routerLink="/">Get All<span class="sr-only">(current)</span></a>
       </li>
       <li class="nav-item">
          <a class="nav-link" routerLink="/byId">Get by Id</a>
       </li>
       <li class="nav-item">
         <a class="nav-link" routerLink="/save">Add Student</a>
       </li>
      </ul>
    </nav>
  </div>
  <router-outlet></router-outlet>
  `
})
export class AppComponent {
}
