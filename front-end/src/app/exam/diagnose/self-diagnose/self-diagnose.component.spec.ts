import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { SelfDiagnoseComponent } from './self-diagnose.component';

describe('SelfDiagnoseComponent', () => {
  let component: SelfDiagnoseComponent;
  let fixture: ComponentFixture<SelfDiagnoseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ SelfDiagnoseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(SelfDiagnoseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
