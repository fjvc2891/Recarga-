import { Component } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { RecargaService } from '../services/recarga.service';
import { OperadorService } from '../services/operador.service';
import { VendedorService } from '../services/vendedor.service';
import { ButtonModule } from 'primeng/button';
import { DropdownModule } from 'primeng/dropdown';
import { ActivatedRoute, Router, RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { CardModule } from 'primeng/card';
import { Operador } from '../models/operador';
import { Vendedor } from '../models/vendedor';


@Component({
  selector: 'app-recarga-form',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    ButtonModule,
    RouterModule,
    InputTextModule,
    InputNumberModule,
    CardModule,
    DropdownModule
  ],
  templateUrl: './recarga-form.component.html',
  styleUrl: './recarga-form.component.scss',
})
export class RecargaFormComponent {
  formRecarga!: FormGroup;
  operadores: Operador[] = []; 
  vendedores: Vendedor[] = [];
  isSaveInProgress: boolean = false;
  edit: boolean = false;

  constructor(
    private fb: FormBuilder,
    private recargaService: RecargaService,
    private activatedRoute: ActivatedRoute,
    private messageService: MessageService,
    private router: Router,
    private operadorService: OperadorService,
    private vendedorService: VendedorService,
  ) {
    this.formRecarga = this.fb.group({
      id: [null],
      operador: ['', Validators.required], // Añade el campo operador
      cantidad: [0, [Validators.required, Validators.min(1)]],      
      valor: ['', [Validators.required, Validators.min(1)]],
      vendedor: ['', Validators.required],
      fechaVenta: [new Date().toISOString(), Validators.required]       
    });
  }

  ngOnInit(): void {
    this.getAllOperadores(); // Cargar operadores
    this.getAllVendedores();
    let id = this.activatedRoute.snapshot.paramMap.get('id');
    if (id !== 'new') {
      this.edit = true;
      this.getRecargaById(+id!);
    }
  }

  getAllOperadores() {
    this.operadorService.getOperadores().subscribe((data) => {
      this.operadores = data;
    });
  }

  getAllVendedores() {
    this.vendedorService.getVendedores().subscribe((data) => {
    this.vendedores = data;
    });
  }

  getRecargaById(id: number) {
    this.recargaService.getRecargaById(id).subscribe({
      next: (foundRecarga) => {
        this.formRecarga.patchValue(foundRecarga);
      },
      error: () => {
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No encontrado',
        });
        this.router.navigateByUrl('/');
      },
    });
  }

  createRecarga() {
    console.log(this.formRecarga.controls); 
    if (this.formRecarga.invalid) {      
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Revise los campos e intente nuevamente',
      });
      return;
    }
    
    this.isSaveInProgress = true;
    this.recargaService
      .createRecarga(this.formRecarga.value)
      .subscribe({
        next: () => {
          this.messageService.add({
            severity: 'success',
            summary: 'Guardado',
            detail: 'Libro guardado correctamente',
          });
          this.isSaveInProgress = false;
          this.router.navigateByUrl('/');
        },
        error: () => {
          this.isSaveInProgress = false;
          this.messageService.add({
            severity: 'error',
            summary: 'Error',
            detail: 'Revise los campos e intente nuevamente',
          });
        },
      });
  }

  updateRecarga() {
    if (this.formRecarga.invalid) {
      this.messageService.add({
        severity: 'error',
        summary: 'Error',
        detail: 'Revise los campos e intente nuevamente',
      });
      return;
    }
    this.isSaveInProgress = true;
    this.recargaService.updateRecarga(this.formRecarga.value).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Guardado',
          detail: 'Libro actualizado correctamente',
        });
        this.isSaveInProgress = false;
        this.router.navigateByUrl('/');
      },
      error: () => {
        this.isSaveInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'Revise los campos e intente nuevamente',
        });
      },
    });
  }
}
