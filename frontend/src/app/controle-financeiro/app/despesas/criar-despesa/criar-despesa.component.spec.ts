/* tslint:disable:no-unused-variable */
import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { DebugElement } from '@angular/core';

import { CriarDespesaComponent } from './criar-despesa.component';

describe('CriarDespesaComponent', () => {
  let component: CriarDespesaComponent;
  let fixture: ComponentFixture<CriarDespesaComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [CriarDespesaComponent],
    }).compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CriarDespesaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
