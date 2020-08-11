import { TestBed } from '@angular/core/testing';

import { OcrPresetsService } from './ocr-presets.service';

describe('OcrPresetsService', () => {
  let service: OcrPresetsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(OcrPresetsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
