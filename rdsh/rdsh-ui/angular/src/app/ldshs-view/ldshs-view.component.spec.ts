import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LdshsViewComponent } from './ldshs-view.component';

describe('LdshsViewComponent', () => {
  let component: LdshsViewComponent;
  let fixture: ComponentFixture<LdshsViewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LdshsViewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LdshsViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
