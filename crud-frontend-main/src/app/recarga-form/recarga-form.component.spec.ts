import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecargaFormComponent } from './recarga-form.component';

describe('RecargaFormComponent', () => {
  let component: RecargaFormComponent;
  let fixture: ComponentFixture<RecargaFormComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [RecargaFormComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecargaFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
