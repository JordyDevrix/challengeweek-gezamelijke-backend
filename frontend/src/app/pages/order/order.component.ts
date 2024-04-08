import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import { OrderService } from '../../services/order.service';
import { GetOrder} from "../../models/getOrder.model";
import {Product} from "../../models/product.model";

@Component({
  selector: 'app-checkout',
  standalone: true,
  imports: [],
  templateUrl: './order.component.html',
  styleUrl: './order.component.scss'
})
export class OrderComponent {
  public orderId: string;
  public  order: GetOrder;
  public loading = true;
  public products: Product[] = [];
  public loadingPayment = false;

  constructor( private route: ActivatedRoute, private orderService: OrderService) {
    this.route.params.subscribe( params => {
      return this.orderId = params["id"];
    });
  }

  ngOnInit() {
    this.orderService
      .getOrderById(this.orderId)
      .subscribe((order: GetOrder) => {
      this.order = order;
      this.loading = false;
      this.minimizeOrder(order);
    });
  }

  // minimize the products list and add the quantity to the product
  private minimizeOrder(order: GetOrder) {
    const products: Product[] = [];
    order.products.forEach((product: Product) => {
      let found = false;
      products.forEach((productInList: Product) => {
        if (productInList.id === product.id) {
          found = true;
          productInList.quantity++;
        }
      });
      if (!found) {
        product.quantity = 1;
        products.push(product);
      }
    });
    this.products = products;
  }

  public  payOrder(status: string) {
    this.loadingPayment = true;
    this.orderService.payOrder(this.orderId, status).subscribe((order: GetOrder) => {
      this.order.status = status;
      this.loadingPayment = false;
    });
  }

  protected readonly parseFloat = parseFloat;
  protected readonly String = String;
}
