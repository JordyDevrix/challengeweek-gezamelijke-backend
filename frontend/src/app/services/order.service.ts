import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { CreateOrder } from "../models/createOrder.model";
import { GetOrder } from "../models/getOrder.model";

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient) {  }

  public getOrders(): Observable<GetOrder[]> {
    return this.http.get<GetOrder[]>('http://s1149771.student.inf-hsleiden.nl:29771/api/private/orders/all');
  }

  public getOrderById(id: string): Observable<GetOrder> {
    return this.http.get<GetOrder>('http://s1149771.student.inf-hsleiden.nl:29771/api/private/orders/' + id);
  }

  public createOrder(order: CreateOrder): Observable<CreateOrder> {
    return this.http.post<CreateOrder>('http://s1149771.student.inf-hsleiden.nl:29771/api/private/orders/create', order);
  }

  // pay existing order
  // payload  is status and id is path variable
  public payOrder(id: string, status: string): Observable<GetOrder> {
    const body = {
      status: status
    }
    return this.http.post<GetOrder>('http://s1149771.student.inf-hsleiden.nl:29771/api/private/orders/pay/' + id,  body);
  }

}
