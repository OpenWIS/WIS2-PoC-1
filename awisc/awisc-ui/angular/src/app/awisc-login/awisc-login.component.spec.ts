import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwiscLoginComponent } from './awisc-login.component';

describe('AwiscLoginComponent', () => {
  let component: AwiscLoginComponent;
  let fixture: ComponentFixture<AwiscLoginComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwiscLoginComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwiscLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
