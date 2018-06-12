import { TestBed, inject } from '@angular/core/testing';

import { PrescriptionService } from './prescription.service';

describe('PrescriptionService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [PrescriptionService]
    });
  });

  it('should be created', inject([PrescriptionService], (service: PrescriptionService) => {
    expect(service).toBeTruthy();
  }));
});
