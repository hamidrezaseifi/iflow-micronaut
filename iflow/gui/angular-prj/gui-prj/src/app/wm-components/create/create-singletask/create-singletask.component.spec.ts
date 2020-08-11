import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateSingletaskComponent } from './create-singletask.component';

describe('CreateSingletaskComponent', () => {
  let component: CreateSingletaskComponent;
  let fixture: ComponentFixture<CreateSingletaskComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CreateSingletaskComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateSingletaskComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
