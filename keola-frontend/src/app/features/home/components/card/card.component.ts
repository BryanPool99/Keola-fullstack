import { Component, Input } from '@angular/core';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-card',
  standalone: true,
  imports: [
    MatCardModule,MatIconModule,RouterModule
  ],
  templateUrl: './card.component.html',
  styleUrl: './card.component.scss'
})
export class CardComponent {
  @Input() icono!: string;  // Ej: 'shopping_cart', 'person', 'inventory'
  @Input() titulo!: string; // Ej: 'Clientes', 'Pedidos'
  @Input() ruta!: string;   // Ej: '/clientes', '/pedidos'
}
