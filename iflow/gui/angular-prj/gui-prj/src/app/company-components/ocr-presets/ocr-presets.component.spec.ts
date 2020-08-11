import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OcrPresetsComponent } from './ocr-presets.component';

describe('OcrPresetsComponent', () => {
  let component: OcrPresetsComponent;
  let fixture: ComponentFixture<OcrPresetsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OcrPresetsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OcrPresetsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
