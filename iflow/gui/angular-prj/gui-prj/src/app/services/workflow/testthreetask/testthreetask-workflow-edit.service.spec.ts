import { TestBed } from '@angular/core/testing';

import { TestthreetaskWorkflowEditService } from './testthreetask-workflow-edit.service';

describe('TestthreetaskWorkflowEditService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TestthreetaskWorkflowEditService = TestBed.get(TestthreetaskWorkflowEditService);
    expect(service).toBeTruthy();
  });
});
