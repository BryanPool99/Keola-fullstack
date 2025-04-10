import { Component } from '@angular/core';
import { BtnToHomeComponent } from '../../shared/components/btn-to-home/btn-to-home.component';

@Component({
  selector: 'app-order',
  standalone: true,
  imports: [BtnToHomeComponent],
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss'
})
export class OrderComponent {

}
