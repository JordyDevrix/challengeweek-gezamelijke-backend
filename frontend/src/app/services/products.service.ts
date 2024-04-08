import { Injectable } from '@angular/core';
import {Product} from "../models/product.model";
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ProductsService {

  private jelle: string = 'http://localhost:8080/api/pub/products/';
  private rick: string = 'http://localhost:8080/api/pub/products/';

  constructor(private http:HttpClient) { }

  public getProductsFromAPI(): Observable<Product[]> {
      return this.http.get<Product[]>('http://localhost:8080/api/pub/products/all');
  }

  public getProducts(): Observable<Product[]> {
    return this.getProductsFromAPI().pipe(
      map((products: Product[]) => {
        for (let i=0; i<products.length; i++) {
          products[i].id = 'RICK-' + products[i].id;
        }
        return products;
      })
    );
  }

  public getProductByIdFromAPI(id: string): Observable<Product> {
    return this.http.get<Product>('http://localhost:8080/api/pub/products/' + id);
  }

  public getProductById(id: string): Observable<Product> {
    let baseUrl: string;
    let strippedId: string;

    if (id.startsWith('RICK-')) {
      baseUrl = this.rick;
      strippedId = id.substring(5); // Strip 'RICK-' prefix
    } else if (id.startsWith('JELLE-')) {
      baseUrl = this.jelle;
      strippedId = id.substring(6); // Strip 'JELLE-' prefix
    } else {
      // Handle unrecognized prefix
      // You can throw an error or return a default value
      throw new Error('Unrecognized product ID prefix');
    }

    return this.http.get<Product>(baseUrl + strippedId);
  }

}
