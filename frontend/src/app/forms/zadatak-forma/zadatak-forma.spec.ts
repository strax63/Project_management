import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ZadatakForma } from './zadatak-forma';

describe('ZadatakForma', () => {
  let component: ZadatakForma;
  let fixture: ComponentFixture<ZadatakForma>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ZadatakForma],
    }).compileComponents();

    fixture = TestBed.createComponent(ZadatakForma);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
