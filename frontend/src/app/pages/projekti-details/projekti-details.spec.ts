import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProjektiDetails } from './projekti-details';

describe('ProjektiDetails', () => {
  let component: ProjektiDetails;
  let fixture: ComponentFixture<ProjektiDetails>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProjektiDetails],
    }).compileComponents();

    fixture = TestBed.createComponent(ProjektiDetails);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
