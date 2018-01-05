import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RDSHComponent } from './rdsh.component';

describe('RDSHComponent', () => {
  let component: RDSHComponent;
  let fixture: ComponentFixture<RDSHComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RDSHComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RDSHComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
