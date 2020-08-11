import { TestBed } from '@angular/core/testing';

import { WorkflowtypePropertySettingService } from './workflowtype-property-setting.service';

describe('WorkflowtypePropertySettingService', () => {
  let service: WorkflowtypePropertySettingService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkflowtypePropertySettingService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
