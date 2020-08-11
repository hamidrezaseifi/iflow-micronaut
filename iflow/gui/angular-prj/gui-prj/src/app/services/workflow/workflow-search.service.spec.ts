import { TestBed } from '@angular/core/testing';

import { WorkflowSearchService } from './workflow-search.service';

describe('WorkflowSearchService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: WorkflowSearchService = TestBed.get(WorkflowSearchService);
    expect(service).toBeTruthy();
  });
});
