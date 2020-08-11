import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateTestthreetaskComponent } from './create-testthreetask.component';

describe('CreateTestthreetaskComponent', () => {
  let component: CreateTestthreetaskComponent;
  let fixture: ComponentFixture<CreateTestthreetaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateTestthreetaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateTestthreetaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
