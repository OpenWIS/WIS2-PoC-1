import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AWISCComponent } from './awisc.component';

describe('AWISCComponent', () => {
  let component: AWISCComponent;
  let fixture: ComponentFixture<AWISCComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AWISCComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AWISCComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
