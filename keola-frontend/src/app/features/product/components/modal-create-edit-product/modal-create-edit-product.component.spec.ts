import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalCreateEditProductComponent } from './modal-create-edit-product.component';

describe('ModalCreateEditProductComponent', () => {
  let component: ModalCreateEditProductComponent;
  let fixture: ComponentFixture<ModalCreateEditProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ModalCreateEditProductComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ModalCreateEditProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
