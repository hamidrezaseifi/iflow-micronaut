import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceOcrDetailComponent } from './invoice-ocr-detail.component';

describe('InvoiceOcrDetailComponent', () => {
  let component: InvoiceOcrDetailComponent;
  let fixture: ComponentFixture<InvoiceOcrDetailComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ InvoiceOcrDetailComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(InvoiceOcrDetailComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
