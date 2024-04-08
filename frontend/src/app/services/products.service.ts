import { Injectable } from '@angular/core';
import {Product} from "../models/product.model";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  constructor(private http:HttpClient) { }

  public getProducts(): Observable<Product[]> {
      return this.http.get<Product[]>('http://s1149771.student.inf-hsleiden.nl:29771/api/pub/products/all');
  }

  public getProductById(id: string): Observable<Product> {
    return this.http.get<Product>('http://s1149771.student.inf-hsleiden.nl:29771/api/pub/products/' + id);
  }

}
