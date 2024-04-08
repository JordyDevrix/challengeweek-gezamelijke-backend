import { Component } from '@angular/core';
import {OrderService} from "../../services/order.service";
import {GetOrder} from "../../models/getOrder.model";
import {Router} from "@angular/router";


@Component({
  selector: 'app-all-orders',
  standalone: true,
  imports: [],
  templateUrl: './all-orders.component.html',
  styleUrl: './all-orders.component.scss'
})
export class AllOrdersComponent {

  public orders : GetOrder[] = [];
  public pagedOrders : GetOrder[][] = [];
  public currentPage : number = 0;
  public loading : boolean = true;
  public notFound : boolean = false;

  constructor(private orderService: OrderService, private router: Router) { }

  ngOnInit() {
    this.orderService.getOrders()
      .subscribe((orders: GetOrder[]) => {
      this.orders = orders;
      this.pagedOrders = this.chunkArray(this.orders, 5);
      this.loading = false;
    }, (error) => {
          if (error.status === 404) {
            this.notFound = true;
          }
          this.loading = false;
        }
      );
  }

  chunkArray(array: any[], chunkSize: number): any[][] {
    let results = [];
    while (array.length) {
      results.push(array.splice(0, chunkSize));
    }
    return results;
  }

  setPage(page: number) {
    this.currentPage = page;
  }

  public nextPage() {
    if (this.currentPage < this.pagedOrders.length - 1) {
      this.currentPage++;
    }
  }

  public previousPage() {
    if (this.currentPage > 0) {
      this.currentPage--;
    }
  }

  public navigateToOrder(orderId: string) {
    this.router.navigate(['/order', orderId]);
  }

}
