import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AtualizarDespesasComponent } from './atualizar-despesas.component';

describe('AtualizarDespesasComponent', () => {
  let component: AtualizarDespesasComponent;
  let fixture: ComponentFixture<AtualizarDespesasComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AtualizarDespesasComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AtualizarDespesasComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
