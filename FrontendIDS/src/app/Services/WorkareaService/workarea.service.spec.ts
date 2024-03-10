import { TestBed } from '@angular/core/testing';

import { WorkareaService } from './workarea.service';

describe('WorkareaService', () => {
  let service: WorkareaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(WorkareaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
