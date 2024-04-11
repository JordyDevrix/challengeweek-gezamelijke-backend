import { Component } from '@angular/core';
import {GetOrder} from "../models/getOrder.model";
import {ActivatedRoute} from "@angular/router";

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

  constructor(
      private activatedRoute: ActivatedRoute,
      private ordersService: OrdersService
  ) { }

  ngOnInit() {
    this.activatedRoute.params.subscribe(params => {
      this.orderId = params['id'];
    });

    this.ordersService
        .getOrderByIndex(this.orderId)
        .subscribe((oitems: OrderItem[]) => {
          this.items = oitems;
          this.total_amount = oitems.reduce((n, {price}) => n + price, 0);
        });
  }

}
