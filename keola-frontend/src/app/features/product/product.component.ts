import { Component, ViewChild } from '@angular/core';
import { BtnToHomeComponent } from '../../shared/components/btn-to-home/btn-to-home.component';
import { CommonModule } from '@angular/common';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { FormControl, ReactiveFormsModule } from '@angular/forms';
import { MatSort, MatSortModule, SortDirection } from '@angular/material/sort';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatInputModule } from '@angular/material/input';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialog } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { StatusPipePipe } from '../../shared/pipes/status-pipe.pipe';
import { ICreateProductRequest, IProductResponse, IRetrieveProductResponse, IUpdateProductRequest } from '../../models/product/Product.interface';
import { GET_BY_ID_PRODUCT, HEADER_TABLE_PRODUCT, LIST_ALL_PRODUCTS, PRODUCT_FILTER, PRODUCT_ID, PRODUCT_RETRIEVE_TYPE } from './constants/product.constants';
import { COLON, COMMA, PAGINATION_LIMIT, PAGINATION_OFFSET, PAGINATION_TOTAL_ELEMENTS } from '../../shared/constants/constants';
import { ProductService } from '../../core/services/product/product.service';
import { ModalCreateEditProductComponent } from './components/modal-create-edit-product/modal-create-edit-product.component';
@Component({
  selector: 'app-product',
  standalone: true,
  imports: [BtnToHomeComponent,
    CommonModule, MatToolbarModule,
    MatCardModule, MatTableModule,
    MatPaginatorModule, MatButtonModule,
    MatFormFieldModule, MatInputModule,
    MatIconModule, MatTooltipModule,
    MatSortModule, ReactiveFormsModule,
    StatusPipePipe
  ],
  templateUrl: './product.component.html',
  styleUrl: './product.component.scss'
})
export class ProductComponent {
  productTitle = 'Productos';
  headers = HEADER_TABLE_PRODUCT;
  displayedColumns = this.headers.map(header => header.key);
  dataSource = new MatTableDataSource<IProductResponse>([]);
  //PAGINATION
  totalElements = PAGINATION_TOTAL_ELEMENTS;
  limit = PAGINATION_LIMIT;
  offset = PAGINATION_OFFSET;
  pageSizeOptions = [2, 4, 10];
  currentSortField: string = 'id';
  currentSortDirection: SortDirection = 'asc';

  searchTerm: FormControl = new FormControl('');

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(
    private readonly _productService: ProductService,
    public dialog: MatDialog
  ) { }

  onSortChange(): void {
    if (this.sort.active && this.sort.direction) {
      this.mapSortField();
      this.paginator.pageIndex = 0;
      this.loadProducts();
    }
  }

  mapSortField(): void {
    const sortMapping: { [key in 'categoryId' | 'categoryDescription' | 'categoryStatus']: string } = {
      'categoryId': 'id',
      'categoryDescription': 'description',
      'categoryStatus': 'status',
    };

    if (this.sort.active in sortMapping) {
      this.currentSortField = sortMapping[this.sort.active as 'categoryId' | 'categoryDescription' | 'categoryStatus'];
    } else {
      this.currentSortField = this.sort.active;
    }
    this.currentSortDirection = this.sort.direction;
  }

  onSearchChange(): void {
    this.paginator.pageIndex = 0;
    this.loadProducts();
  }

  ngAfterViewInit(): void {
    this.initializePaginator();
    setTimeout(() => {
      this.sort.active = this.currentSortField;
      this.sort.direction = this.currentSortDirection;
      this.sort.sortChange.subscribe(() => this.onSortChange());
      this.loadProducts();
    });
  }

  private initializePaginator(): void {
    this.paginator.pageSize = this.limit;
    this.paginator.pageSizeOptions = this.pageSizeOptions;

    this.paginator.page
      .subscribe(() => {
        this.handlePageChange();
      });
  }

  private handlePageChange(): void {
    this.offset = this.paginator.pageIndex * this.paginator.pageSize;
    this.limit = this.paginator.pageSize;
    this.loadProducts();
  }

  private loadProducts(): void {
    const filterValue = this.buildFilter(this.searchTerm.value);
    const paginationParams = {
      offset: this.paginator.pageIndex * this.paginator.pageSize,
      limit: this.paginator.pageSize,
      filter: filterValue,
      sort: `${this.currentSortField}${COMMA}${this.currentSortDirection}`
    };

    this._productService.getProducts(paginationParams)
      .subscribe({
        next: (res: IRetrieveProductResponse) => this.handleSuccessResponse(res),
        error: (err) => this.handleError(err)
      });
  }

  private buildFilter(searchTerm: string | undefined): string {
    if (searchTerm && searchTerm.length > 0) {
      return `${PRODUCT_RETRIEVE_TYPE}${COLON}${LIST_ALL_PRODUCTS}${COMMA}${PRODUCT_FILTER}${COLON}${searchTerm}`;
    } else {
      return `${PRODUCT_RETRIEVE_TYPE}${COLON}${LIST_ALL_PRODUCTS}`;
    }
  }

  private handleSuccessResponse(res: IRetrieveProductResponse): void {
    this.dataSource.data = res.products;
    if (res.metadata) {
      this.totalElements = res.metadata.totalElements;
      // Actualizar paginador con los datos del servidor
      if (this.paginator) {
        this.paginator.length = this.totalElements;
        this.paginator.pageIndex = Math.floor(res.metadata.offset / res.metadata.limit);
      }
    }
  }

  private handleError(err: any): void {
    console.error('Error al obtener los productos:', err);
    if (this.paginator) {
      this.paginator.pageIndex = 0;
      this.paginator.pageSize = this.limit;
    }
  }

  openModalProduct(product?: IProductResponse): void {
    if (product) {
      const filter = `${PRODUCT_RETRIEVE_TYPE}${COLON}${GET_BY_ID_PRODUCT}${COMMA}${PRODUCT_ID}${COLON}${product.id}`;
      this._productService.getProductById({ filter }).subscribe({
        next: (res: IRetrieveProductResponse) => {
          if (res.products.length > 0) {
            const productData = res.products[0];
            this.openModalWithData(productData);
          }
        },
        error: (err) => console.error('Error obteniendo detalle de productos:', err)
      });
    } else {
      this.openModalWithData();
    }
  }

  openModalWithData(product?: IProductResponse): void {
    const dialogRef = this.dialog.open(ModalCreateEditProductComponent, {
      width: '500px',
      data: product || null
    });

    dialogRef.afterClosed()
      .subscribe((result: ICreateProductRequest | IUpdateProductRequest) => {
        if (result) {
          if (product) {
            this._productService.updateProduct(result as IUpdateProductRequest)
              .subscribe({
                next: () => {
                  this.loadProducts();
                },
                error: (err) => console.error("Error al actualizar product", err)
              });
          } else {
            this._productService.createProduct(result as ICreateProductRequest).subscribe({
              next: () => {
                this.loadProducts();
              },
              error: (err) => console.error("Error al crear producto", err)
            })
          }
        }
      })
  }
}
