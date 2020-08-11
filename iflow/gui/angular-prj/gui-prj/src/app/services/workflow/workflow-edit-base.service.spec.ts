import { TestBed } from '@angular/core/testing';

import { WorkflowEditBase.ServiceService } from './workflow-edit-base.service';

describe('WorkflowEditBaseService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkflowEditBaseService = TestBed.get(WorkflowEditBaseService);
    expect(service).toBeTruthy();
  });
});
