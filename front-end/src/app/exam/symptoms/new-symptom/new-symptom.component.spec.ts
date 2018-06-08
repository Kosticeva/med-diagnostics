import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NewSymptomComponent } from './new-symptom.component';

describe('NewSymptomComponent', () => {
  let component: NewSymptomComponent;
  let fixture: ComponentFixture<NewSymptomComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NewSymptomComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NewSymptomComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
