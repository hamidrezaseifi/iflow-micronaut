import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditSingleTaskComponent } from './edit-single-task.component';

describe('EditSingleTaskComponent', () => {
  let component: EditSingleTaskComponent;
  let fixture: ComponentFixture<EditSingleTaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditSingleTaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditSingleTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
