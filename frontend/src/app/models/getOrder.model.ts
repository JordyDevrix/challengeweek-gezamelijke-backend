import {Product} from "./product.model";

export class GetOrder {

  public id: string
  public products: Product[]
  public totalPrice: number
  public orderDate: string
  public status: string

  constructor(id: string, products: Product[], totalPrice: number, orderDate: string, status: string) {
    this.id = id;
    this.products = products;
    this.totalPrice = totalPrice;
    this.orderDate = orderDate;
    this.status = status;
  }
}
