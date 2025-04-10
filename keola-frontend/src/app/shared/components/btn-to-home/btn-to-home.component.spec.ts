import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BtnToHomeComponent } from './btn-to-home.component';

describe('BtnToHomeComponent', () => {
  let component: BtnToHomeComponent;
  let fixture: ComponentFixture<BtnToHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [BtnToHomeComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(BtnToHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
