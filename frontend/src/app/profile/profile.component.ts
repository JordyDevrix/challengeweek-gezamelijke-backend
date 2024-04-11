import { Component } from '@angular/core';
import {GetOrder} from "../models/getOrder.model";
import {ActivatedRoute} from "@angular/router";
import { OrderService } from '../services/order.service';
import { Product } from '../models/product.model';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})

export class ProfileComponent {
  public items: GetOrder[] = new Array<GetOrder>();
  public orderId: number;
  public total_amount:number=0;
  public products: Product[] = new Array<Product>();

  constructor(
      private activatedRoute: ActivatedRoute,
      private orderService: OrderService
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.orderId = params['id'];
    });

    // this.orderService
    //     .getOrderById(this.orderId)
    //     .subscribe((oitems: OrderItem[]) => {
    //       this.items = oitems;
    //       this.total_amount = oitems.reduce((n, {price}) => n + price, 0);
    //     });
  }

}
