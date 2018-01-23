import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LdshsAdminComponent } from './ldshs-admin.component';

describe('LdshsAdminComponent', () => {
  let component: LdshsAdminComponent;
  let fixture: ComponentFixture<LdshsAdminComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ LdshsAdminComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(LdshsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
