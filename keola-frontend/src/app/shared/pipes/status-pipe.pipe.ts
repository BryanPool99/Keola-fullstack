import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'statusPipe',
  standalone: true
})
export class StatusPipePipe implements PipeTransform {

  transform(value: number): string {
    return value === 1 ? 'Activo' : 'Inactivo';
  }
}
