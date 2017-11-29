import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LdshsMainComponent } from './ldshs-main.component';

describe('LdshsMainComponent', () => {
  let component: LdshsMainComponent;
  let fixture: ComponentFixture<LdshsMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LdshsMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LdshsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
