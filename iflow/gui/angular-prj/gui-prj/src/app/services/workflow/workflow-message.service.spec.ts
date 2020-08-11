import { TestBed } from '@angular/core/testing';

import { WorkflowMessageService } from './workflow-message.service';

describe('WorkflowMessageService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkflowMessageService = TestBed.get(WorkflowMessageService);
    expect(service).toBeTruthy();
  });
});
