import { Injectable } from '@angular/core';
import { environment } from '../../../../environments/environment';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { PATH_PRODUCT, UNICA_USER } from '../../../shared/constants/constants';
import { ICreateProductRequest, IRetrieveProductResponse, IUpdateProductRequest } from '../../../models/product/Product.interface';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private readonly _baseUrl!: string;
  private readonly _connection = environment;

  constructor(
    private readonly _http: HttpClient
  ) {
    this._baseUrl = this._connection.productUrlBase;
  }

  getProducts(body: { offset: number; limit: number; filter: string; sort: string; }) {
    const params = new HttpParams()
      .set('filter', body.filter)
      .set('sort', body.sort)
      .set('limit', body.limit.toString())
      .set('offset', body.offset.toString())

    const headers = new HttpHeaders()
      .set('UNICA-User', UNICA_USER)

    const url = `${this._baseUrl}${PATH_PRODUCT}?${params.toString()}`
    return this._http.get<IRetrieveProductResponse>(url, { headers });
  }

  getProductById(body: { filter: string; }) {
    const params = new HttpParams()
      .set('filter', body.filter)

    const headers = new HttpHeaders()
      .set('UNICA-User', UNICA_USER)

    const url = `${this._baseUrl}${PATH_PRODUCT}?${params.toString()}`
    return this._http.get<IRetrieveProductResponse>(url, { headers });
  }

  createProduct(body: ICreateProductRequest) {
    const headers = new HttpHeaders()
      .set('UNICA-User', UNICA_USER)
    const url = `${this._baseUrl}${PATH_PRODUCT}`
    return this._http.post(url, body, { headers });
  }

  updateProduct(body: IUpdateProductRequest) {
    const headers = new HttpHeaders()
      .set('UNICA-User', UNICA_USER)

    const url = `${this._baseUrl}${PATH_PRODUCT}`
    return this._http.patch(url, body, { headers });
  }
}
