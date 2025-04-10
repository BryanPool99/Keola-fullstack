import { IAdditionalData, IMetadata } from "../../shared/models/InterfacesShared.interface";


export interface IRetrieveProductResponse {
    products: IProductResponse[]
    metadata?: IMetadata
}

export interface IProductResponse {
    id: number;
    name: string;
    status: number;
    stock: number;
    price: number;
    categoryId: number;
    categoryDescription: string;
    additionalData?: IAdditionalData[];
}

export interface ICreateProductRequest {
    description: string;
    status: number;
    stock: number;
    price: number;
    categoryId: number;
    additionalData?: IAdditionalData[];
}

export interface IUpdateProductRequest extends ICreateProductRequest{
    id: number
}