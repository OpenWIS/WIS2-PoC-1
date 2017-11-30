import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AwiscSearchComponent } from './awisc-search.component';

describe('AwiscSearchComponent', () => {
  let component: AwiscSearchComponent;
  let fixture: ComponentFixture<AwiscSearchComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AwiscSearchComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AwiscSearchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
