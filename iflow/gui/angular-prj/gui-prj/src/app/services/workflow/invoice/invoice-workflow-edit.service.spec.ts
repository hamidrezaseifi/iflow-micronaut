import { TestBed } from '@angular/core/testing';

import { InvoiceWorkflowEditService } from './invoice-workflow-edit.service';

describe('InvoiceWorkflowEditService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: InvoiceWorkflowEditService = TestBed.get(InvoiceWorkflowEditService);
    expect(service).toBeTruthy();
  });
});
