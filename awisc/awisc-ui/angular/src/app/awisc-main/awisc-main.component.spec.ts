import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwiscMainComponent } from './awisc-main.component';

describe('AwiscMainComponent', () => {
  let component: AwiscMainComponent;
  let fixture: ComponentFixture<AwiscMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwiscMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwiscMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
