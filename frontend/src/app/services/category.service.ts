import { Injectable } from '@angular/core';
import {Category} from "../models/category.model";
import {HttpClient} from "@angular/common/http";
import {catchError, forkJoin, map, Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {


  private baseUrl: string = 'http://localhost:8081/api/pub/categories/';


  constructor(private http:HttpClient) { }

  public getCategories(): Observable<Category[]> {
    return this.http.get<Category[]>(this.baseUrl+"all");
  }

  public getCategoryById(id: string): Observable<Category> {
    return this.http.get<Category>(this.baseUrl + id);
  }


}
