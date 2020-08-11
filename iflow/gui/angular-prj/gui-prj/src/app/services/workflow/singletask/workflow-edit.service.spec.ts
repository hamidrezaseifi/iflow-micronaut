import { TestBed } from '@angular/core/testing';

import { WorkflowEditService } from './workflow-edit.service';

describe('WorkflowEditService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkflowEditService = TestBed.get(WorkflowEditService);
    expect(service).toBeTruthy();
  });
});
