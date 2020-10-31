import { Routes, RouterModule } from '@angular/router';

import { AuthenticationService } from './services/authentication.service';

import { HomeComponent } from './home';
import { HomeTestComponent } from './hometest/home-test.component';
import { AboutComponent } from './about';
import { LoginComponent } from './login/login.component';

import { WorkflowListComponent } from './wm-components/workflow-list/workflow-list.component';
import { WorkflowCreateComponent } from './wm-components/create/workflow-create/workflow-create.component';
import { CreateSingletaskComponent } from './wm-components/create/create-singletask/create-singletask.component';
import { CreateInvoiceComponent } from './wm-components/create/create-invoice/create-invoice.component';
import { CreateTestthreetaskComponent } from './wm-components/create/create-testthreetask/create-testthreetask.component';
import { EditInvoiceComponent } from './wm-components/edit/edit-invoice/edit-invoice.component';
import { EditSingleTaskComponent } from './wm-components/edit/edit-single-task/edit-single-task.component';
import { EditTestthreeTaskComponent } from './wm-components/edit/edit-testthree-task/edit-testthree-task.component';
import { UserListComponent } from './company-components/user-list/user-list.component';
import { CompanyInfoComponent } from './company-components/company-info/company-info.component';
import { DepartmentsListComponent } from './company-components/departments-list/departments-list.component';
import { WorkflowtypePropertySettingComponent } from './company-components/workflowtype-property-setting/workflowtype-property-setting.component';
import { OcrPresetsComponent } from './company-components/ocr-presets/ocr-presets.component';



const routes: Routes = [
    //{ path: '', component: HomeTestComponent },
    { path: '', component: HomeComponent, canActivate: [AuthenticationService] },
    { path: 'auth/login', component: LoginComponent },
    { path: 'about', component: AboutComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/list', component: WorkflowListComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/create', component: WorkflowCreateComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/create/singletask', component: CreateSingletaskComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/create/invoice', component: CreateInvoiceComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/create/testthreetask', component: CreateTestthreetaskComponent, canActivate: [AuthenticationService] },

    { path: 'workflow/edit/singletaskworkflowtype/:identity', component: EditSingleTaskComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/edit/threetaskworkflowtype/:identity', component: EditTestthreeTaskComponent, canActivate: [AuthenticationService] },
    { path: 'workflow/edit/invoiceworkflowtype/:identity', component: EditInvoiceComponent, canActivate: [AuthenticationService] },

    { path: 'users/list', component: UserListComponent, canActivate: [AuthenticationService] },

    { path: 'departments/list', component: DepartmentsListComponent, canActivate: [AuthenticationService] },

    { path: 'company/info', component: CompanyInfoComponent, canActivate: [AuthenticationService] },
    { path: 'company/ocrpresets', component: OcrPresetsComponent, canActivate: [AuthenticationService] },

    // otherwise redirect to home
    { path: '**', redirectTo: '', canActivate: [AuthenticationService] }
];

export const appRoutingModule = RouterModule.forRoot(routes);
