import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiagnoseComponent } from './diagnose.component';

describe('DiagnoseComponent', () => {
  let component: DiagnoseComponent;
  let fixture: ComponentFixture<DiagnoseComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiagnoseComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiagnoseComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
