import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewIcPatientComponent } from './new-ic-patient.component';

describe('NewIcPatientComponent', () => {
  let component: NewIcPatientComponent;
  let fixture: ComponentFixture<NewIcPatientComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewIcPatientComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewIcPatientComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
