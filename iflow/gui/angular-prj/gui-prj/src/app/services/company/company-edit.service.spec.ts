import { TestBed } from '@angular/core/testing';

import { CompanyEditService } from './company-edit.service';

describe('CompanyEditService', () => {
  let service: CompanyEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CompanyEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
