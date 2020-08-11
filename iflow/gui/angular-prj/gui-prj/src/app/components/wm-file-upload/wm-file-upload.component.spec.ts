import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WmFileUploadComponent } from './wm-file-upload.component';

describe('WmFileUploadComponent', () => {
  let component: WmFileUploadComponent;
  let fixture: ComponentFixture<WmFileUploadComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WmFileUploadComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WmFileUploadComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
