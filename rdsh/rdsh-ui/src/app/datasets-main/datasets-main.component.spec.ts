import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DatasetsMainComponent } from './datasets-main.component';

describe('DatasetsMainComponent', () => {
  let component: DatasetsMainComponent;
  let fixture: ComponentFixture<DatasetsMainComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DatasetsMainComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DatasetsMainComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
