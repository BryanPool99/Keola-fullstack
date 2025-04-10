import { Component } from '@angular/core';
import { BtnToHomeComponent } from '../../shared/components/btn-to-home/btn-to-home.component';

@Component({
  selector: 'app-client',
  standalone: true,
  imports: [BtnToHomeComponent],
  templateUrl: './client.component.html',
  styleUrl: './client.component.scss'
})
export class ClientComponent {

}
