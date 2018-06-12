import { TestBed, inject } from '@angular/core/testing';

import { AllergyService } from './allergy.service';

describe('AllergyService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AllergyService]
    });
  });

  it('should be created', inject([AllergyService], (service: AllergyService) => {
    expect(service).toBeTruthy();
  }));
});
