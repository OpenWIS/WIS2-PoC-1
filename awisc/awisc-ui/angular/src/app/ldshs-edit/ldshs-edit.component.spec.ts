import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LdshsEditComponent } from './ldshs-edit.component';

describe('LdshsEditComponent', () => {
  let component: LdshsEditComponent;
  let fixture: ComponentFixture<LdshsEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LdshsEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LdshsEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
