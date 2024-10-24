import { Component } from '@angular/core';
import { ButtonModule } from 'primeng/button';
import { CardModule } from 'primeng/card';
import { Recarga } from '../models/recarga';
import { RecargaService } from '../services/recarga.service';
import { RouterModule } from '@angular/router';
import { MessageService } from 'primeng/api';
import { CommonModule, DatePipe } from '@angular/common'; // Importa CommonModule y DatePipe


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [ButtonModule, CardModule, RouterModule, CommonModule],
  providers: [DatePipe], // Agrega DatePipe como provider
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss',
})
export class HomeComponent {
  recargas: Recarga[] = [];
  isDeleteInProgress: boolean = false;
  constructor(
    private recargaService: RecargaService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.getAllrecargas();
  }


  
  getAllrecargas() {
    this.recargaService.getRecargas().subscribe((data) => {
      this.recargas = data;
    });
  }

  deleterecarga(id: number) {
    this.isDeleteInProgress = true;
    this.recargaService.deleteRecarga(id).subscribe({
      next: () => {
        this.messageService.add({
          severity: 'success',
          summary: 'Correcto',
          detail: 'Recarga eliminado',
        });
        this.isDeleteInProgress = false;
        this.getAllrecargas();
      },
      error: () => {
        this.isDeleteInProgress = false;
        this.messageService.add({
          severity: 'error',
          summary: 'Error',
          detail: 'No se pudo eliminar el Recarga',
        });
      },
    });
  }
}
