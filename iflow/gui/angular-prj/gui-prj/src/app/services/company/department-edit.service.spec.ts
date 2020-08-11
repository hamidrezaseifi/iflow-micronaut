import { TestBed } from '@angular/core/testing';

import { DepartmentEditService } from './department-edit.service';

describe('DepartmentEditService', () => {
  let service: DepartmentEditService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DepartmentEditService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
