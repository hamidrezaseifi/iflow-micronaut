import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WmAssignListComponent } from './wm-assign-list.component';

describe('WmAssignListComponent', () => {
  let component: WmAssignListComponent;
  let fixture: ComponentFixture<WmAssignListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WmAssignListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WmAssignListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
