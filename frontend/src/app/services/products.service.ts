import { Injectable } from '@angular/core';
import {Product} from "../models/product.model";
import {HttpClient} from "@angular/common/http";
import {catchError, forkJoin, map, Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private baseUrl: string = 'http://localhost:8081/api/pub/products/';

  constructor(private http:HttpClient) { }

  public getProducts(): Observable<Product[]> {
    return this.http.get<Product[]>(this.baseUrl);
  }

  public getProductById(id: string): Observable<Product> {
    return this.http.get<Product>(this.baseUrl + id);
  }


}
