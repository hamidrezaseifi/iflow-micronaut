import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EditTestthreeTaskComponent } from './edit-testthree-task.component';

describe('EditTestthreeTaskComponent', () => {
  let component: EditTestthreeTaskComponent;
  let fixture: ComponentFixture<EditTestthreeTaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditTestthreeTaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditTestthreeTaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
