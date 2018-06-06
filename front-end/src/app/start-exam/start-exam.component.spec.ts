import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { StartExamComponent } from './start-exam.component';

describe('StartExamComponent', () => {
  let component: StartExamComponent;
  let fixture: ComponentFixture<StartExamComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ StartExamComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(StartExamComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
