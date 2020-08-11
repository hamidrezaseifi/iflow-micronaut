import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkflowInlineviewComponent } from './workflow-inlineview.component';

describe('WorkflowInlineviewComponent', () => {
  let component: WorkflowInlineviewComponent;
  let fixture: ComponentFixture<WorkflowInlineviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ WorkflowInlineviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(WorkflowInlineviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
