import {Product} from "./product.model";

export class CreateOrder {

  public products: Product[]
  public status: string

  constructor(products: Product[], status: string) {
    this.products = products;
    this.status = status;
  }
}
