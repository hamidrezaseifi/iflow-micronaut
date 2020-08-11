import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowtypePropertySettingComponent } from './workflowtype-property-setting.component';

describe('WorkflowtypePropertySettingComponent', () => {
  let component: WorkflowtypePropertySettingComponent;
  let fixture: ComponentFixture<WorkflowtypePropertySettingComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowtypePropertySettingComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowtypePropertySettingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
