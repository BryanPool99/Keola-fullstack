
import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: 'keola',
        loadComponent: () => import('./shared/components/layout/layout.component')
          .then(m => m.LayoutComponent),
        children: [
          {
            path: 'home',
            loadComponent: () => import('./features/home/home.component').then(m => m.HomeComponent)
          },
          {
            path: 'orders',
            loadComponent: () => import('./features/order/order.component').then(m => m.OrderComponent)
          },
          {
            path: 'products',
            loadComponent: () => import('./features/product/product.component').then(m => m.ProductComponent)
          },
          {
            path: 'clients',
            loadComponent: () => import('./features/client/client.component').then(m => m.ClientComponent)
          },
          { path: '', redirectTo: 'home', pathMatch: 'full' }
        ]
      },
      {
        path: '',
        redirectTo: 'keola/home',
        pathMatch: 'full'
      }
];
