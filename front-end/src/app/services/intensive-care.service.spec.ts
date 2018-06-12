import { TestBed, inject } from '@angular/core/testing';

import { IntensiveCareService } from './intensive-care.service';

describe('IntensiveCareService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [IntensiveCareService]
    });
  });

  it('should be created', inject([IntensiveCareService], (service: IntensiveCareService) => {
    expect(service).toBeTruthy();
  }));
});
