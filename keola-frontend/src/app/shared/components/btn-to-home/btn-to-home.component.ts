import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
@Component({
  selector: 'app-btn-to-home',
  standalone: true,
  imports: [MatButtonModule, MatIconModule],
  templateUrl: './btn-to-home.component.html',
  styleUrl: './btn-to-home.component.scss'
})
export class BtnToHomeComponent {
  constructor(private router: Router) {}

  goToHome() {
    this.router.navigate(['/']); // Navega a la ruta Home (/)
  }
}
