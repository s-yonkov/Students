import {ModuleWithProviders} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';

import {GetAllStudentsComponent} from './components/getAll/getAll.component';
import {GetStudentByIdComponent} from './components/getById/getById.component';
import { SaveStudentComponent } from './components/save/saveStudent.component';

const appRoutes: Routes = [
    {
        path: '',
        component: GetAllStudentsComponent
    },
    {
        path: 'byId',
        component: GetStudentByIdComponent
    },
    {
        path: 'save',
        component: SaveStudentComponent
    }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
