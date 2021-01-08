import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule } from '@angular/router';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TranslateModule, TranslateLoader} from '@ngx-translate/core';
import { TranslateHttpLoader} from '@ngx-translate/http-loader';
import { ResizableModule } from 'angular-resizable-element';
import { FormsModule } from '@angular/forms';
//import { DataTableModule } from 'ng-seven-datatable';
import { MatNativeDateModule } from '@angular/material/core';
import { MatTableModule } from '@angular/material/table';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';
import { PdfViewerModule } from 'ng2-pdf-viewer';
import { CookieService } from 'ngx-cookie-service';
import { AuthInterceptor } from './services/auth.interceptor';
import { HTTP_INTERCEPTORS } from '@angular/common/http';

import {IFlowMaterialModules} from './material-module';


import { AppComponent } from './app.component';
import { appRoutingModule } from './app.routing';
import { GlobalService } from './services/global.service';
import { AuthenticationService } from './services/authentication.service';
import { WorkflowMessageService } from './services/workflow/workflow-message.service';


import { TopBarComponent } from './top-bar/top-bar.component';
import { FooterComponent } from './footer/footer.component';
import { MessageBarComponent } from './message-bar/message-bar.component';
import { ErrorDialogComponent } from './components/error-dialog/error-dialog.component';
import { LoadingDialogComponent } from './components/loading-dialog/loading-dialog.component';

import { HomeComponent } from './home';
import { AboutComponent } from './about';
import { WorkflowListComponent } from './wm-components/workflow-list/workflow-list.component';
import { WorkflowCreateComponent } from './wm-components/create/workflow-create/workflow-create.component';
import { CreateSingletaskComponent } from './wm-components/create/create-singletask/create-singletask.component';
import { CreateInvoiceComponent } from './wm-components/create/create-invoice/create-invoice.component';
import { CreateTestthreetaskComponent } from './wm-components/create/create-testthreetask/create-testthreetask.component';
import { EditInvoiceComponent } from './wm-components/edit/edit-invoice/edit-invoice.component';
import { EditSingleTaskComponent } from './wm-components/edit/edit-single-task/edit-single-task.component';
import { EditTestthreeTaskComponent } from './wm-components/edit/edit-testthree-task/edit-testthree-task.component';
import { WorkflowInlineviewComponent } from './wm-components/workflow-inlineview/workflow-inlineview.component';
import { SelectUserComponent } from './components/select-user/select-user.component';
import { InvoiceOcrDetailComponent } from './wm-components/invoice-ocr-detail/invoice-ocr-detail.component';
import { FilePreviewComponent } from './components/file-preview/file-preview.component';
import { WmFileUploadComponent } from './components/wm-file-upload/wm-file-upload.component';
import { WmAssignListComponent } from './components/wm-assign-list/wm-assign-list.component';
import { UserListComponent } from './company-components/user-list/user-list.component';
import { CompanyInfoComponent } from './company-components/company-info/company-info.component';
import { DepartmentsListComponent } from './company-components/departments-list/departments-list.component';
import { TextListComponent } from './components/text-list/text-list.component';
import { OcrPresetsComponent } from './company-components/ocr-presets/ocr-presets.component';
import { LoginComponent } from './login/login.component';
import { AngularSplitModule } from 'angular-split';

export function createTranslateLoader(http: HttpClient) {
    return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}


@NgModule({
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    HttpClientModule,
    appRoutingModule,
    BrowserAnimationsModule,
    ResizableModule,
    MatTableModule,
    MatNativeDateModule,
    IFlowMaterialModules,
    FormsModule,
    NgxExtendedPdfViewerModule,
    PdfViewerModule,
    AngularSplitModule,
    TranslateModule.forRoot({
        loader: {
            provide: TranslateLoader,
            useFactory: (createTranslateLoader),
            deps: [HttpClient]
        }
    }),
  ],
  declarations: [
    AppComponent,
    TopBarComponent,
    FooterComponent,
    MessageBarComponent,
    ErrorDialogComponent,
    LoadingDialogComponent,
    HomeComponent,
    AboutComponent,
    WorkflowCreateComponent,
    WorkflowListComponent,
    CreateSingletaskComponent,
    CreateInvoiceComponent,
    CreateTestthreetaskComponent,
    EditInvoiceComponent,
    EditSingleTaskComponent,
    EditTestthreeTaskComponent,
    WorkflowInlineviewComponent,
    SelectUserComponent,
    InvoiceOcrDetailComponent,
    FilePreviewComponent,
    WmFileUploadComponent,
    WmAssignListComponent,
    UserListComponent,
    CompanyInfoComponent,
    DepartmentsListComponent,
    TextListComponent,
    OcrPresetsComponent,
    LoginComponent,

  ],
  providers: [
	  GlobalService,
	  AuthenticationService,
	  WorkflowMessageService,
	  CookieService,
	  {
        provide: HTTP_INTERCEPTORS,
        useClass: AuthInterceptor,
        multi: true,
    }

  ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }


/*
Copyright Google LLC. All Rights Reserved.
Use of this source code is governed by an MIT-style license that
can be found in the LICENSE file at http://angular.io/license
*/
